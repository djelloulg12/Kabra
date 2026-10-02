package com.example.data.api

import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object GeminiChatService {

    private const val MODEL_NAME = "gemini-3.5-flash"
    private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/$MODEL_NAME:generateContent"

    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private const val SYSTEM_PROMPT = """
أنت "المرشد والمرافق الثقافي واللغوي الذكي لبرنامج التبادل الجزائري النيجيري في غرداية 2026".
أنت شريك محادثة تفاعلي ودود ومتقن للغتين: العربية (الفصحى واللهجة الجزائرية المهذبة) والفرنسية.
جمهورك المستهدف هو: وفد جمهورية النيجر الشقيقة (39 متكوناً في مجالي تركيب ألواح الطاقة الشمسية وكهرباء المعمار) والمنظمين الجزائريين وسكان ولاية غرداية.

مهامك الرئيسية:
1. المساعدة في التدريب والممارسة اللغوية الحية (Language Practice Partner):
   - تحدث بلغة واضحة وداعمة، وقدم عند الحاجة الترجمة الفرنسية أو النطق الصوتي للعبارات بالدارجة أو العربية.
2. الإجابة عن كل ما يتعلق ببرنامج الدورة وأنشطتها السياحية والثقافية:
   - استقبال الوفد، جولة بني يزقن وسيدي عباز وقبر مفدي زكرياء.
   - رحلة سوق غرداية وقصر تافيلالت البيئي.
   - السهرات الفلكلورية: الديوان، التيزمارين (الغايطة)، الدندون.
   - رحلة متليلي ومتحف المجاهد والشهداء وواحة سبسب ومنتجع الهدار.
   - ذكرى ثورة أول نوفمبر والسينما التاريخية.
   - حمام زلفانة المعدني والاستجمام.
   - أداء صلاة الجمعة بمسجد عقبة بن نافع ببوهراوة.
   - الحفل الختامي وتوزيع الشهادات بحضور السيد والي غرداية وشركة SKTM.
3. المساعدة في المصطلحات التقنية في الطاقة الشمسية وكهرباء المعمار بالعربية والفرنسية.
4. إبراز روح التضامن والإخاء الإفريقي وكرم الضيافة الجزائري الأصيل.
كن دائماً ودوداً، مختصراً عند الحاجة، وداعماً للتعلم والممارسة.
"""

    suspend fun sendMessage(
        history: List<Pair<String, String>>, // (role "user" / "model", text)
        userMessage: String
    ): String = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY

        if (apiKey.isNullOrBlank() || apiKey == "MY_GEMINI_API_KEY") {
            // Provide high-quality smart contextual response if API key is not yet set
            return@withContext generateSmartFallback(userMessage)
        }

        try {
            val jsonRoot = JSONObject()

            // System instruction
            val sysInstruction = JSONObject()
            val sysParts = JSONArray()
            sysParts.put(JSONObject().put("text", SYSTEM_PROMPT))
            sysInstruction.put("parts", sysParts)
            jsonRoot.put("systemInstruction", sysInstruction)

            // Contents array
            val contentsArray = JSONArray()

            // Add last few turns of conversation for context
            val recentHistory = history.takeLast(6)
            for ((role, text) in recentHistory) {
                val contentObj = JSONObject()
                contentObj.put("role", role)
                val partsArr = JSONArray()
                partsArr.put(JSONObject().put("text", text))
                contentObj.put("parts", partsArr)
                contentsArray.put(contentObj)
            }

            // Current message
            val currentContent = JSONObject()
            currentContent.put("role", "user")
            val currentParts = JSONArray()
            currentParts.put(JSONObject().put("text", userMessage))
            currentContent.put("parts", currentParts)
            contentsArray.put(currentContent)

            jsonRoot.put("contents", contentsArray)

            // Generation config
            val genConfig = JSONObject()
            genConfig.put("temperature", 0.7)
            genConfig.put("topP", 0.95)
            jsonRoot.put("generationConfig", genConfig)

            val urlWithKey = "$BASE_URL?key=$apiKey"
            val body = jsonRoot.toString().toRequestBody("application/json; charset=utf-8".toMediaType())

            val request = Request.Builder()
                .url(urlWithKey)
                .post(body)
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()

            if (!response.isSuccessful || responseBody.isNullOrBlank()) {
                return@withContext generateSmartFallback(userMessage)
            }

            val responseJson = JSONObject(responseBody)
            val candidates = responseJson.optJSONArray("candidates")
            if (candidates != null && candidates.length() > 0) {
                val firstCandidate = candidates.getJSONObject(0)
                val content = firstCandidate.optJSONObject("content")
                val parts = content?.optJSONArray("parts")
                if (parts != null && parts.length() > 0) {
                    val reply = parts.getJSONObject(0).optString("text")
                    if (reply.isNotBlank()) {
                        return@withContext reply
                    }
                }
            }

            return@withContext generateSmartFallback(userMessage)
        } catch (e: Exception) {
            return@withContext generateSmartFallback(userMessage)
        }
    }

    private fun generateSmartFallback(query: String): String {
        val lower = query.lowercase()

        return when {
            lower.contains("bonjour") || lower.contains("salut") || lower.contains("مرحبا") || lower.contains("سلام") -> {
                """
أهلاً وسهلاً بك في غرداية! Bonjour et bienvenue à Ghardaïa!
يسعدني جداً أن أكون رفيقك اللغوي والثقافي خلال هذا البرنامج المتميز لوفد النيجر الشقيق.
أنا هنا لمساعدتك على:
1. التدرب على المحادثات اليومية بالعربية والفرنسية.
2. الاستفسار عن تفاصيل الرحلات والأنشطة الأسبوعية ومبرراتها.
3. مراجعة مصطلحات الطاقة الشمسية وكهرباء المعمار.
بماذا ترغب أن نبدأ؟ Comment puis-je vous aider aujourd'hui?
                """.trimIndent()
            }
            lower.contains("سولار") || lower.contains("شمس") || lower.contains("solaire") || lower.contains("كهرباء") || lower.contains("panneau") -> {
                """
⚡ **مصطلحات الطاقة الشمسية وكهرباء المعمار (Énergie Solaire & Bâtiment)**:

• **لوح شمسي ضوئي**: Panneau solaire photovoltaïque
• **عاكس / محوّل التيار**: Onduleur (Inverter)
• **بطارية التخزين**: Batterie de stockage solaire
• **هيكل التثبيت والزاوية**: Structure de fixation et angle d'inclinaison
• **قاطع الدارة الكهربائية**: Disjoncteur différentiel
• **تأريض السلامة**: Mise à la terre (Grounding)
• **مخطط التوزيع الكهربائي**: Schéma de distribution électrique

💡 *نصيحة تطبيقية*: خلال ورشات التكوين مع مهندسي شركة SKTM، احرص دائماً على اختبار قطبية الألواح وتأمين العزل قبل تشغيل المحول!
                """.trimIndent()
            }
            lower.contains("تافيلالت") || lower.contains("tafilalt") -> {
                """
🏛️ **قصر تافيلالت البيئي (Ksar Tafilalet)**:
هو أول قصر بيئي مستدام في الجزائر، يقع على هضبة صخرية محاذية لبني يزقن.
• **سبب برمجته**: تعريف الوفد بالامتداد الحضري الحديث الذي يمزج بين الهندسة الطينية الميزابية وتقنيات التنمية المستدامة (طاقة شمسية، تدوير مياه الصرف بالنباتات، حديقة بيئية).
• **فاز بجائزة المدينة المستدامة الدولية في مؤتمر COP22**.
                """.trimIndent()
            }
            lower.contains("متليلي") || lower.contains("metlili") || lower.contains("مجاهد") -> {
                """
🇩🇿 **رحلة متليلي ومتحف المجاهد**:
تمثل هذه المحطة البعد التاريخي والوطني الأبرز في البرنامج:
1. **متحف المجاهد ومقبرة الشهداء**: التعرف على بطولات الثورة التحريرية والتضحيات من أجل السيادة والاستقلال، والبعد التحرري الإفريقي.
2. **القصر القديم بمتليلي**: التراث العمراني لقبائل الشعانبة الأماجد.
3. **واحة سبسب ومنتجع الهدار**: واحة النخيل والاستجمام الصحي.
                """.trimIndent()
            }
            lower.contains("زلفانة") || lower.contains("zelfana") || lower.contains("حمام") -> {
                """
♨️ **واحة وحمام زلفانة المعدني**:
تقع زلفانة على بعد 65 كم شرق غرداية، وتشتهر بمياهها المعدنية الحارة الطبيعية (41.5° مئوية).
• **سبب اختيارها**: تم برمجتها في الأسبوع الخامس لتكون محطة راحة واستجمام عضلي وبدني للمتكونين بعد أسابيع من الجهد في الورشات التكوينية.
                """.trimIndent()
            }
            lower.contains("جمعة") || lower.contains("صلاة") || lower.contains("عقبة") || lower.contains("priere") -> {
                """
🕌 **برنامج صلاة الجمعة الأسبوعي**:
تقام صلاة الجمعة أسبوعياً بمسجد **"عقبة بن نافع"** بحي بوهراوة العليا:
• **التوقيت**: الانطلاق 12:15 - الصلاة 12:30 إلى 13:30.
• **الهدف**: تعزيز الروابط الروحية والأخوية بين الشعبين الجزائري والنيجري، ومشاركة سكان المنطقة في أجواء إيمانية دافئة، تليها جلسة تعارف مع إمام المسجد والمصلين.
                """.trimIndent()
            }
            else -> {
                """
مرحباً بك! يسعدني إجابتك حول أي استفسار يتعلق ببرنامج الوفد النيجيري في غرداية:
• يمكنك السؤال عن تفاصيل ومواقيت أي يوم من الأيام العشرة.
• أو الاستفسار عن سبب ومبررات اختيار أي نشاط (المعالم، السهرات، المتاحف، الواحات).
• أو التدرب على محادثة بالفرنسية أو العربية الدارجة.
أنا في خدمتك دائماً! Je suis à votre entière disposition!
                """.trimIndent()
            }
        }
    }
}
