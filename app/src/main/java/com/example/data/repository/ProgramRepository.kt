package com.example.data.repository

import com.example.R
import com.example.data.model.AppLanguage
import com.example.data.model.CulturalLandmark
import com.example.data.model.EventCategory
import com.example.data.model.MapLandmark
import com.example.data.model.Participant
import com.example.data.model.ProgramDay
import com.example.data.model.ScheduleParagraph

object ProgramRepository {

    val programOverviewAr = """
        في إطار تنفيذ برنامج السيد عبد المجيد تبون، رئيس الجمهورية، الرامي إلى تعزيز التعاون والتضامن مع الدول الشقيقة والصديقة، وتجسيداً لتوصيات السيد والي ولاية غرداية، تم إعداد برنامج تكوين ورسكلة لفائدة رعايا جمهورية النيجر الشقيقة.
        يتضمن البرنامج إلى جانب التكوين أنشطة ثقافية وسياحية وترفيهية خلال عطلات نهاية الأسبوع، للتعريف بتاريخ الجزائر وذاكرتها الوطنية وتراثها الثقافي، وإبراز خصوصية ولاية غرداية ومحيطها، وتعزيز أواصر التعاون والتقارب بين الشعبين الشقيقين في إطار حسن الاستقبال والضيافة.
    """.trimIndent()

    val programOverviewFr = """
        Dans le cadre de la mise en œuvre du programme de Monsieur Abdelmadjid Tebboune, Président de la République, visant à renforcer la coopération et la solidarité avec les pays frères et amis, et conformément aux orientations de Monsieur le Wali de Ghardaïa, un programme de formation et de recyclage a été élaboré au profit des ressortissants de la République sœur du Niger.
        Ce programme comprend, outre le volet technique, des activités culturelles, touristiques et récréatives durant les week-ends pour faire découvrir l'histoire de l'Algérie, sa mémoire nationale et son patrimoine culturel, valoriser les spécificités de la wilaya de Ghardaïa et consolider la fraternité et le rapprochement entre les deux peuples frères.
    """.trimIndent()

    fun getProgramOverview(isArabic: Boolean) = if (isArabic) programOverviewAr else programOverviewFr

    val programOverview: String get() = programOverviewAr

    val programDays: List<ProgramDay> = listOf(
        // Arrival & Reception
        ProgramDay(
            id = "day_reception_1",
            dayNumber = 0,
            dateString = "الأربعاء 30 سبتمبر 2026",
            dateStringFr = "Mercredi 30 Septembre 2026",
            gregorianDate = "2026-09-30",
            title = "وصول واستقبال الوفد النيجيري الشقيق",
            titleFr = "Arrivée et Accueil de la Délégation Nigérienne",
            shortSubtitle = "مطار مفدي زكرياء الدولي – غرداية",
            shortSubtitleFr = "Aéroport International Moufdi Zakaria – Ghardaïa",
            location = "مطار مفدي زكرياء ومقر الإقامة بواد نشو",
            locationFr = "Aéroport Moufdi Zakaria & Résidence Oued Nechou",
            timeRange = "22:00 ليلاً",
            timeRangeFr = "22h00 (Soir)",
            category = EventCategory.WELCOME,
            illustrationRes = R.drawable.ic_guide_airport,
            paragraphs = listOf(
                ScheduleParagraph(
                    id = "rec_p1",
                    time = "22:00",
                    title = "وصول الطائرة واستقبال الرعايا بمطار مفدي زكرياء",
                    description = "استقبال رسمي وأخوي حار للوفد النيجيري القادم إلى الجزائر، وتسهيل الإجراءات ومرافقتهم.",
                    titleFr = "Atterrissage et accueil officiel à l'aéroport Moufdi Zakaria",
                    descriptionFr = "Accueil chaleureux et fraternel de la délégation à son arrivée en Algérie avec facilitation des formalités douanières et aéroportuaires."
                ),
                ScheduleParagraph(
                    id = "rec_p2",
                    time = "23:00",
                    title = "الانتقال نحو مقر الإقامة بواد نشو",
                    description = "تأمين نقل الوفد إلى مركز ومعهد التكوين المهني بواد نشو وتناول وجبة خفيفة والاستراحة.",
                    titleFr = "Transfert vers le centre d'hébergement à Oued Nechou",
                    descriptionFr = "Transport par bus confortables vers les instituts de formation à Oued Nechou, collation de bienvenue et repos."
                )
            ),
            justification = "بداية البرنامج بالاستقبال الرسمي الأخوي بالمطار لتوفير أقصى درجات الراحة للوفد الشقيق بعد رحلة السفر الجوي.",
            justificationFr = "Inauguration du séjour par un accueil d'honneur chaleureux dès l'aéroport afin de garantir le confort optimal de nos hôtes nigériens après leur vol.",
            culturalHighlights = listOf("حسن الضيافة الجزائرية", "مطار مفدي زكرياء الدولي", "مركز واد نشو"),
            culturalHighlightsFr = listOf("Hospitalité légendaire algérienne", "Aéroport International Moufdi Zakaria", "Campus de formation Oued Nechou")
        ),

        // Housing & Orientation
        ProgramDay(
            id = "day_reception_2",
            dayNumber = 0,
            dateString = "الخميس 01 أكتوبر 2026",
            dateStringFr = "Jeudi 01 Octobre 2026",
            gregorianDate = "2026-10-01",
            title = "تنظيم الإقامة وتوزيع البرنامج التكويني",
            titleFr = "Installation et Présentation du Programme",
            shortSubtitle = "معهد ومركز واد نشو للذكور والإناث",
            shortSubtitleFr = "Instituts CFPA et IEP Oued Nechou",
            location = "واد نشو – غرداية",
            locationFr = "Oued Nechou – Ghardaïa",
            timeRange = "خلال اليوم",
            timeRangeFr = "Journée complète",
            category = EventCategory.WELCOME,
            illustrationRes = R.drawable.ic_guide_residence,
            paragraphs = listOf(
                ScheduleParagraph(
                    id = "org_p1",
                    time = "09:00 – 12:00",
                    title = "ترتيب وتثبيت الغرف وتوفير الإمكانيات اللوجستية",
                    description = "توزيع المشاركين على مقرات الإقامة (مركز الذكور ومعهد الإناث) وضمان كل سبل الراحة.",
                    titleFr = "Répartition des chambres et logistique d'accueil",
                    descriptionFr = "Attribution des logements (centre masculin et institut féminin) et vérification de tous les équipements de confort."
                ),
                ScheduleParagraph(
                    id = "org_p2",
                    time = "14:00 – 17:00",
                    title = "عرض وتوزيع برنامج التكوين والأنشطة الترفيهية",
                    description = "جلسة تمهيدية لعرض محاور الرسكلة والتكوين المهني وجدول الرحلات الثقافية الأسبوعية.",
                    titleFr = "Présentation du calendrier pédagogique et culturel",
                    descriptionFr = "Séance d'orientation présentant les modules de perfectionnement professionnel et le calendrier des excursions du week-end."
                )
            ),
            justification = "تأمين الجاهزية التامة للمشاركين وتوفير بيئة تعليمية واستقرار نفسي يضمن انطلاقة متميزة للدورة.",
            justificationFr = "Garantir les meilleures conditions matérielles et psychologiques pour assurer le plein succès de la session de formation.",
            culturalHighlights = listOf("الهياكل التكوينية المتطورة بواد نشو", "التأطير البيداغوجي المتميز"),
            culturalHighlightsFr = listOf("Infrastructures modernes de formation", "Encadrement pédagogique d'excellence")
        ),

        // Activity 1: Welcome Tour
        ProgramDay(
            id = "day_tour_welcome",
            dayNumber = 1,
            dateString = "الجمعة 02 أكتوبر 2026",
            dateStringFr = "Vendredi 02 Octobre 2026",
            gregorianDate = "2026-10-02",
            title = "الجولة الترحيبية: إطلالة بني يزقن وسيدي عباز + سهرة فلكلورية",
            titleFr = "Visite de Bienvenue : Panorama de Beni Isguen et Sidi Abbaz",
            shortSubtitle = "بني يزقن وسيدي عباز",
            shortSubtitleFr = "Beni Isguen & Belvédère Sidi Abbaz",
            location = "بني يزقن – سيدي عباز",
            locationFr = "Beni Isguen & Sidi Abbaz",
            timeRange = "16:00 إلى 22:30 (ساعتان ونصف للجولة + السهرة)",
            timeRangeFr = "16h00 à 22h30 (2h30 de visite + dîner et veillée)",
            category = EventCategory.WELCOME,
            illustrationRes = R.drawable.ic_guide_sidi_abbaz,
            paragraphs = listOf(
                ScheduleParagraph("w_p1", "16:00", "التجمع والانطلاق من مقر الإقامة", "الالتقاء ببهو مركز الإقامة والصعود إلى الحافلات المجهزة", "Rassemblement et départ de la résidence", "Embarquement dans les bus touristiques climatisés depuis Oued Nechou"),
                ScheduleParagraph("w_p2", "16:30 – 17:00", "زيارة إطلالة بني يزقن وقبر مفدي زكرياء", "إطلالة بانورامية ساحرة على وادي ميزاب والوقوف تخليداً لشاعر الثورة مؤلف النشيد الوطني 'قسماً'", "Panorama de Beni Isguen et tombe de Moufdi Zakaria", "Vue panoramique splendide sur la vallée et hommage au poète national auteur de l'hymne Qassaman"),
                ScheduleParagraph("w_p3", "17:00", "التوجه نحو ساحة الوئام بسيدي عباز", "الانتقال المنظم نحو المعلم السياحي التاريخي بسيدي عباز", "Trajet vers l'esplanade de la Concorde à Sidi Abbaz", "Déplacement vers le belvédère monumental dominant l'oasis"),
                ScheduleParagraph("w_p4", "17:00 – 18:30", "جولة سياحية بسيدي عباز وصور تذكارية", "استكشاف الهندسة المعمارية العريقة والتقاط صور جماعية وتذكارية عند الغروب", "Visite guidée et photos souvenirs", "Découverte architecturale au coucher du soleil et prises de vues panoramiques"),
                ScheduleParagraph("w_p5", "18:30", "الانطلاق للعودة إلى مقر الإقامة", "العودة عبر الحافلات بعد جولة خفيفة وممتعة", "Départ pour le retour", "Retour confortable en autocar vers le campus"),
                ScheduleParagraph("w_p6", "19:00", "الوصول إلى مقر الإقامة بواد نشو", "أخذ قسط من الراحة والاستعداد لوجبة العشاء", "Arrivée à la résidence", "Temps de repos avant le dîner convivial"),
                ScheduleParagraph("w_p7", "19:30", "العشاء بمقر الإقامة", "مائدة عشاء ترحيبية تجمع الوفد والطاقم المؤطر", "Dîner de bienvenue", "Repas partagé entre la délégation et l'équipe d'encadrement algérienne"),
                ScheduleParagraph("w_p8", "20:45 – 22:30", "سهرة ترحيبية مبهجة", "سهرة موسيقية خفيفة وأحاديث ودية لكسر الجليد والتعارف", "Soirée festive et conviviale", "Ambiance musicale saharienne et échanges fraternels pour célébrer les premiers jours")
            ),
            justification = "تعتبر هذه الرحلة الترحيبية القصيرة بمثابة ترحيب أولي بالوفد النيجيري وتمهيد مريح للبرنامج. تم اختيار إطلالة بني يزقن لتقديم نظرة بانورامية لجمال العمران المحلي، مع ربط الوفد برمزية تاريخية وأدبية هامة من خلال الوقوف بجهة قبر شاعر الثورة ومؤلف النشيد الوطني 'مفدي زكرياء'. كما تتيح الجولة حول معلم سيدي عباز فرصة للتعرف السريع على أحد المعالم السياحية البارزة والجميلة بالمنطقة في جو ممتع وخفيف لا يسبب الإرهاق للوفد في أيامهم الأولى.",
            justificationFr = "Cette sortie conviviale constitue une première immersion en douceur pour nos frères nigériens. Le belvédère de Beni Isguen offre un panorama grandiose sur le génie urbain mozabite, allié à la forte portée historique du mausolée de Moufdi Zakaria. Le site de Sidi Abbaz permet une promenade agréable sans fatigue pour débuter le séjour.",
            culturalHighlights = listOf("قصر بني يزقن العتيق", "مرقد شاعر الثورة مفدي زكرياء", "معلم سيدي عباز وساحة الوئام", "العمران الميزابي المصنف عالمياً"),
            culturalHighlightsFr = listOf("Cité fortifiée de Beni Isguen", "Mausolée du poète révolutionnaire Moufdi Zakaria", "Belvédère de Sidi Abbaz et Place de la Concorde", "Architecture du M'Zab classée UNESCO")
        ),

        // Excursion 1
        ProgramDay(
            id = "day_excursion_1",
            dayNumber = 2,
            dateString = "السبت 10 أكتوبر 2026",
            dateStringFr = "Samedi 10 Octobre 2026",
            gregorianDate = "2026-10-10",
            title = "الرحلة الأولى: وسط مدينة غرداية، السوق، بني يزقن، تافيلالت",
            titleFr = "1ère Excursion : Centre de Ghardaïa, Marché, Beni Isguen, Tafilalet",
            shortSubtitle = "الأسبوع الأول – وسط المدينة وقصر تافيلالت البيئي",
            shortSubtitleFr = "1ère semaine – Patrimoine urbain & Ksar Écologique",
            location = "وسط غرداية – سوق غرداية – بني يزقن – تافيلالت",
            locationFr = "Centre de Ghardaïa – Souk – Beni Isguen – Ksar Tafilalet",
            timeRange = "07:00 إلى 12:30",
            timeRangeFr = "07h00 à 12h30",
            category = EventCategory.EXCURSION,
            illustrationRes = R.drawable.ic_guide_tafilalet,
            paragraphs = listOf(
                ScheduleParagraph("ex1_p1", "07:00", "التجمع والانطلاق من مقر الإقامة", "الانطلاق في الصباح الباكر للاستمتاع بجو الصباح المعتدل", "Rassemblement et départ matinal", "Départ à la fraîche pour profiter pleinement de la matinée"),
                ScheduleParagraph("ex1_p2", "07:30 – 08:15", "جولة تعريفية بوسط مدينة غرداية", "التعرف على الشوارع الرئيسية، النسيج العمراني، والأزقة التقليدية", "Tour d'orientation du centre de Ghardaïa", "Découverte des grandes artères, des ruelles sinueuses et de l'architecture traditionnelle"),
                ScheduleParagraph("ex1_p3", "08:15 – 10:00", "زيارة سوق غرداية العتيق", "جولة في ساحة السوق التاريخية، دكاكين الزرابي، الفخار، الجلود والتوابل والتمور والتعرف على حركة التجارة الأصيلة", "Immersion au grand souk historique", "Visite des échoppes de tapis mozabites réputés, dattes Deglet Nour, épices et poteries sahariennes"),
                ScheduleParagraph("ex1_p4", "10:00 – 11:15", "زيارة بني يزقن وسورها", "جولة داخل القصر المشهور بنظافته ونظامه التسييري الاجتماعي الفريد وسوره المحصن", "Visite de la cité fortifiée de Beni Isguen", "Exploration de la ville sainte, son organisation sociétale séculaire et ses remparts préservés"),
                ScheduleParagraph("ex1_p5", "11:15 – 12:30", "زيارة قصر تافيلالت النموذجي وصورة جماعية", "زيارة القصر البيئي المعاصر الحائز على جوائز دولية في الاستدامة والهندسة الترابية، والتقاط صور جماعية", "Visite du ksar écologique modèle de Tafilalet", "Découverte de l'éco-cité primée internationalement pour ses technologies solaires et sa gestion de l'eau"),
                ScheduleParagraph("ex1_p6", "12:30", "العودة إلى مقر الإقامة", "العودة للراحة وتناول وجبة الغداء", "Retour à la résidence", "Retour pour le déjeuner et repos du week-end")
            ),
            justification = "تمثل هذه الرحلة المدخل الأول لتعريف الوفد بمدينة غرداية، ولذلك جُمعت فيها عناصر مختلفة: المدينة، السوق، التراث العمراني، والامتداد الحضري الحديث. كما تتيح للوفد التعرف على خصوصية المنطقة بصورة مباشرة، بدل الاكتفاء بالمعلومات النظرية، وتمنحه فرصة للتواصل مع الحياة اليومية والحرف والمنتجات المحلية.",
            justificationFr = "Première grande étape initiatique au cœur du M'Zab : elle associe patrimoine historique, vitalité commerciale du souk, modèle sociétal de Beni Isguen et modernité durable de l'éco-ksar de Tafilalet.",
            culturalHighlights = listOf("سوق ساحة غرداية التاريخي", "صناعة الزرابي التقليدية", "قصر تافيلالت البيئي المستدام", "العمارة الطينية الصحراوية"),
            culturalHighlightsFr = listOf("Grand Souk séculaire de Ghardaïa", "Artisanat du tapis mozabite", "Ksar écologique modèle de Tafilalet", "Architecture bioclimatique en terre")
        ),

        // Folklore Evening 1
        ProgramDay(
            id = "day_folklore_1",
            dayNumber = 3,
            dateString = "الجمعة 16 أكتوبر 2026",
            dateStringFr = "Vendredi 16 Octobre 2026",
            gregorianDate = "2026-10-16",
            title = "الأمسية الفنية الأولى: الفرقة الفولكلورية «الديوان»",
            titleFr = "1ère Soirée Artistique : Troupe Folklorique « Diwan »",
            shortSubtitle = "الأسبوع الثاني – مساءً – موسيقى الديوان الشعبية",
            shortSubtitleFr = "2ème semaine – Rythmes spirituels du Diwan",
            location = "غرداية",
            locationFr = "Ghardaïa",
            timeRange = "19:30 إلى 22:00",
            timeRangeFr = "19h30 à 22h00",
            category = EventCategory.FOLKLORE,
            illustrationRes = R.drawable.ic_guide_folklore,
            paragraphs = listOf(
                ScheduleParagraph("f1_p1", "19:30 – 19:45", "افتتاح الأمسية الفنية", "كلمة ترحيبية وتقديم نبذة عن تاريخ فن الديوان الجزائري وارتباطه بالتراث الإفريقي", "Ouverture de la soirée", "Mot de bienvenue et présentation historique des racines sahariennes du Diwan"),
                ScheduleParagraph("f1_p2", "19:45 – 22:00", "العرض الفني لموسيقى الديوان", "عزف الآلات الإيقاعية والقرقابو والقمبري، وأداء الأناشيد الشعبية التراثية", "Concert envoûtant du Diwan", "Prestation musicale aux sons du guembri, des karkabous et des percussions polyrythmiques"),
                ScheduleParagraph("f1_p3", "22:00", "صور جماعية مع أعضاء الفرقة", "التقاط صور تذكارية مع الفنانين بأزيائهم التقليدية المميزة", "Séance photos avec les artistes", "Photos souvenirs aux côtés des musiciens en tenue d'apparat traditionnelle")
            ),
            justification = "تأتي الأمسية الأولى بعد التعرف الأولي إلى غرداية لتقديم الفن الشعبي الجزائري بوصفه وسيلة للتقارب الثقافي. ولا يقتصر الهدف على الترفيه، وإنما إتاحة تجربة مباشرة للوفد مع الموسيقى والأداء واللباس والأجواء الاحتفالية الجزائرية، بما يضيف بعداً إنسانياً إلى البرنامج التكويني.",
            justificationFr = "Le Diwan incarne la passerelle culturelle par excellence reliant l'Algérie subsaharienne au reste du continent. Cette soirée valorise la musique comme vecteur de communion humaine et d'intégration.",
            culturalHighlights = listOf("موسيقى الديوان والقناوة", "آلة القمبري الوترية", "إيقاعات القرقابو", "التقارب الثقافي الجزائري النيجيري"),
            culturalHighlightsFr = listOf("Musique Diwan et Gnaoua", "Luth Guembri traditionnel", "Castagnettes Karkabous", "Passerelle culturelle Algérie-Niger")
        ),

        // Excursion 2
        ProgramDay(
            id = "day_excursion_2",
            dayNumber = 4,
            dateString = "السبت 24 أكتوبر 2026",
            dateStringFr = "Samedi 24 Octobre 2026",
            gregorianDate = "2026-10-24",
            title = "الرحلة الثانية: متليلي الشعانبة وسبسب ومنتجع الهدار",
            titleFr = "2ème Excursion : Metlili Chaamba, Sebseb & Complexe Al-Haddar",
            shortSubtitle = "الأسبوع الثالث – التاريخ الوطني والواحات الطبيعية",
            shortSubtitleFr = "3ème semaine – Mémoire Nationale & Oasis Saharienne",
            location = "متليلي – واحة سبسب – منتجع الهدار",
            locationFr = "Metlili – Oasis de Sebseb – Complexe Al-Haddar",
            timeRange = "07:00 إلى 13:45",
            timeRangeFr = "07h00 à 13h45",
            category = EventCategory.EXCURSION,
            illustrationRes = R.drawable.ic_guide_metlili,
            paragraphs = listOf(
                ScheduleParagraph("ex2_p1", "07:00", "التجمع بمقر الإقامة", "التحضير والتفتيش اللوجستي", "Rassemblement à la résidence", "Vérifications logistiques et embarquement"),
                ScheduleParagraph("ex2_p2", "07:15", "الانطلاق من غرداية", "رحلة عبر الطريق الوطني نحو مدينة متليلي العريقة", "Départ de Ghardaïa", "Traversée de la steppe vers la cité historique de Metlili"),
                ScheduleParagraph("ex2_p3", "08:15 تقريباً", "الوصول إلى متليلي", "استقبال محلي واستراحة وجيزة", "Arrivée à Metlili", "Accueil par les autorités locales et pause rafraîchissante"),
                ScheduleParagraph("ex2_p4", "08:15 – 09:30", "زيارة متحف المجاهد ومقبرة الشهداء", "استعراض مآثر الثورة التحريرية المظفرة، صور وبطولات الشهداء والتضحيات من أجل الاستقلال والسيادة", "Visite du Musée du Moudjahid & Cimetière des Martyrs", "Découverte des archives de la Révolution de Libération nationale et hommage aux sacrifices pour l'indépendance"),
                ScheduleParagraph("ex2_p5", "09:30 – 10:45", "زيارة القصر القديم بمتليلي", "اكتشاف المعمار الدفاعي الأصيل لشعب الشعانبة وأسلوب الحياة القديم في القصر", "Visite du Vieux Ksar de Metlili", "Exploration des fortifications ancestrales et de l'habitat guerrier des tribus Chaamba"),
                ScheduleParagraph("ex2_p6", "10:45 – 11:15", "التوجه إلى واحة سبسب وجولة بالواحة", "معاينة بساتين النخيل ونظام السقي التقليدي والتنوع البيولوجي الصحراوي", "Arrivée à l'oasis de Sebseb", "Promenade au milieu des palmeraies et observation du système traditionnel d'irrigation par séguia"),
                ScheduleParagraph("ex2_p7", "11:15 – 12:30", "التوجه إلى منتجع الهدار السياحي", "استراحة استجمامية في واحة النخيل والمرافق الترفيهية العصرية", "Détente au Complexe Touristique Al-Haddar", "Moment de relaxation dans un cadre enchanteur alliant palmiers et équipements de loisirs"),
                ScheduleParagraph("ex2_p8", "12:30", "الانطلاق نحو غرداية", "رحلة العودة", "Retour vers Ghardaïa", "Trajet retour en autocar"),
                ScheduleParagraph("ex2_p9", "13:45", "الوصول إلى مقر الإقامة", "الراحة واستكمال برنامج عطلة الأسبوع", "Arrivée à Oued Nechou", "Repos et quartier libre pour le reste du week-end")
            ),
            justification = "هذه الرحلة هي المحطة ذات البعد الوطني والتاريخي الأوضح في البرنامج، حيث أُدرجت زيارة متحف المجاهد في مقدمة الرحلة لإبراز جانب من تاريخ الجزائر وذاكرتها الوطنية، والتعريف بالتضحيات التي ارتبطت بمسار التحرير الوطني، بما يعكس قيمة السيادة الوطنية واستقلال القرار الجزائري. ثم ينتقل الوفد من الذاكرة الوطنية إلى الذاكرة العمرانية والحضارية من خلال زيارة القصر القديم، قبل الانتقال إلى واحة سبسب وما تمثله من خصوصية بيئية واقتصادية واجتماعية للمنطقة. أما إدراج منتجع الهدار في نهاية اليوم فيحقق التوازن بين الجانب التاريخي والثقافي من جهة، والراحة والاستجمام من جهة أخرى، وبذلك تكون الرحلة مبنية على تسلسل مقصود: التاريخ الوطني ← التراث العمراني ← الطبيعة والواحات ← الاستجمام.",
            justificationFr = "Cette excursion s'articule autour d'une progression réfléchie : Histoire nationale au Musée du Moudjahid → Patrimoine architectural au Ksar de Metlili → Nature saharienne à l'oasis de Sebseb → Détente au complexe Al-Haddar.",
            culturalHighlights = listOf("متحف المجاهد بمتليلي", "تاريخ الثورة التحريرية والتضامن الإفريقي", "قصر متليلي الشعانبة القديم", "واحة سبسب ونخيل الدقلة", "منتجع الهدار"),
            culturalHighlightsFr = listOf("Musée du Moudjahid", "Révolution algérienne et solidarité africaine", "Vieux Ksar des Chaamba", "Oasis de palmiers de Sebseb", "Complexe touristique Al-Haddar")
        ),

        // Folklore Evening 2
        ProgramDay(
            id = "day_folklore_2",
            dayNumber = 5,
            dateString = "الجمعة 30 أكتوبر 2026",
            dateStringFr = "Vendredi 30 Octobre 2026",
            gregorianDate = "2026-10-30",
            title = "الأمسية الفنية الثانية: الفرقة الشعبية «التيزمارين» (الغايطة)",
            titleFr = "2ème Soirée Artistique : Troupe Traditionnelle « Tizmarines »",
            shortSubtitle = "الأسبوع الرابع – مساءً – التراث الموسيقي الشعبي",
            shortSubtitleFr = "4ème semaine – Flûte Gaïta et folklore bédouin",
            location = "غرداية",
            locationFr = "Ghardaïa",
            timeRange = "19:30 إلى 22:00",
            timeRangeFr = "19h30 à 22h00",
            category = EventCategory.FOLKLORE,
            illustrationRes = R.drawable.ic_guide_folklore,
            paragraphs = listOf(
                ScheduleParagraph("f2_p1", "19:30 – 19:45", "افتتاح الأمسية", "الترحيب بالوفد وتعريفهم بطابع التيزمارين والغايطة البدوية الأصيلة", "Ouverture de la veillée", "Introduction aux traditions musicales nomades et à l'instrument Gaïta"),
                ScheduleParagraph("f2_p2", "19:45 – 22:00", "العرض الفني الشعبي للفرقة", "نغمات الغايطة والطبل والبارود والأهازيج الصحراوية التقليدية", "Spectacle folklorique haut en couleur", "Danses guerrières, rythmes de tambour saharien et envolées de Gaïta"),
                ScheduleParagraph("f2_p3", "22:00", "صور جماعية مع أعضاء الفرقة", "توثيق الأجواء الاحتفالية والأزياء التقليدية للفرقة", "Photos souvenirs de groupe", "Immortalisation de la soirée en costumes folkloriques traditionnels")
            ),
            justification = "اختيار فرقة شعبية ثانية، مختلفة عن «الديوان»، يهدف إلى إظهار التنوع في التعبير الفني المحلي وعدم اختزال الثقافة الجزائرية في نمط فني واحد. كما تأتي هذه الأمسية بعد الرحلة الثانية لتكون مناسبة خفيفة للترويح عن الوفد واستعادة النشاط قبل استكمال المرحلة الأخيرة من التكوين.",
            justificationFr = "Le choix d'une troupe bédouine met en valeur la diversité artistique algérienne et offre un moment réjouissant de décompression avant l'entame de la dernière ligne droite du cursus technique.",
            culturalHighlights = listOf("آلة الغايطة الشعبية", "إيقاعات البارود والشعانبة", "تنوع الفولكلور الصحراوي الجزائري"),
            culturalHighlightsFr = listOf("Flûte ancestrale Gaïta", "Rythmes bédouins et danses traditionnelles", "Richesse du folklore saharien")
        ),

        // Revolution Day Film
        ProgramDay(
            id = "day_revolution_cinema",
            dayNumber = 6,
            dateString = "الأحد 01 نوفمبر 2026",
            dateStringFr = "Dimanche 01 Novembre 2026",
            gregorianDate = "2026-11-01",
            title = "الأمسية السينمائية: ذكرى اندلاع الثورة التحريرية المباركة",
            titleFr = "Soirée Ciné-Histoire : Commémoration du 1er Novembre 1954",
            shortSubtitle = "عرض فيلم ومحاضرة حول الثورة الجزائرية التحريرية",
            shortSubtitleFr = "Conférence et projection d'un film sur la Révolution",
            location = "غرداية",
            locationFr = "Ghardaïa",
            timeRange = "18:30 إلى 22:30",
            timeRangeFr = "18h30 à 22h30",
            category = EventCategory.CINEMA_HISTORY,
            illustrationRes = R.drawable.ic_guide_moufdi,
            paragraphs = listOf(
                ScheduleParagraph("rev_p1", "18:30 – 19:30", "محاضرة تاريخية عن ثورة أول نوفمبر المجيدة", "عرض أبعاد ثورة التحرير الجزائرية، الدعم الإفريقي المتبادل ومبادئ التحرر والسيادة", "Conférence commémorative du 1er Novembre", "Exposé historique sur le déclenchement de la Révolution, ses idéaux de liberté et la solidarité panafricaine"),
                ScheduleParagraph("rev_p2", "19:30 – 20:00", "استراحة ونقاش تفاعلي", "تبادل الأسئلة والأفكار حول التاريخ المشترك والتضامن الإفريقي", "Pause-débat interactif", "Échanges fraternels sur l'histoire commune et l'émancipation des peuples africains"),
                ScheduleParagraph("rev_p3", "20:00 – 22:30", "عرض فيلم وثائقي/تاريخي ملحمي عن الثورة", "مشاهدة عمل سينمائي يوثق كفاح الشعب الجزائري وتضحيات الشهداء الأبرار", "Projection d'un film historique majeur", "Immersion cinématographique retraçant l'épopée héroïque du peuple algérien pour son indépendance")
            ),
            justification = "تخليد الذكرى الوطنية الكبرى لاندلاع ثورة التحرير الجزائرية في 1 نوفمبر 1954، وإطلاع الإخوة النيجيريين على محطة فارقة في تاريخ التحرر الإفريقي، لترسيخ الوعي المشترك بقيمة النضال والكرامة والسيادة.",
            justificationFr = "Célébration de la Fête nationale du 1er Novembre 1954. Cette soirée partage avec nos homologues nigériens l'épopée fondatrice de la dignité et de la souveraineté africaines.",
            culturalHighlights = listOf("عيد الثورة 1 نوفمبر 1954", "تاريخ الجزائر النضالي", "العمق الإفريقي للثورة الجزائرية", "أناشيد الثورة والحرية"),
            culturalHighlightsFr = listOf("Fête Nationale du 1er Novembre", "Histoire héroïque de libération", "Dimension panafricaine du combat algérien", "Chants patriotiques")
        ),

        // Excursion 3: Zelfana Thermal
        ProgramDay(
            id = "day_excursion_3",
            dayNumber = 7,
            dateString = "السبت 07 نوفمبر 2026",
            dateStringFr = "Samedi 07 Novembre 2026",
            gregorianDate = "2026-11-07",
            title = "الرحلة الثالثة: حمام زلفانة المعدني والاستجمام",
            titleFr = "3ème Excursion : Station Thermale & Balnéaire de Zelfana",
            shortSubtitle = "الأسبوع الخامس – واحة زلفانة وحماماتها المعدنية الحارة",
            shortSubtitleFr = "5ème semaine – Eaux chaudes sulfurées & Relaxation",
            location = "زلفانة",
            locationFr = "Zelfana (65 km à l'Est de Ghardaïa)",
            timeRange = "07:00 إلى 13:00",
            timeRangeFr = "07h00 à 13h00",
            category = EventCategory.RELAXATION,
            illustrationRes = R.drawable.ic_guide_zelfana,
            paragraphs = listOf(
                ScheduleParagraph("z_p1", "07:00", "التجمع بمقر الإقامة", "الاستعداد والتأكد من المستلزمات الشخصية للاستحمام والاستجمام", "Rassemblement matinal", "Préparation des effets personnels pour la baignade thermale"),
                ScheduleParagraph("z_p2", "07:15", "الانطلاق من غرداية", "الانتقال نحو مدينة زلفانة الحموية السياحية", "Départ de Ghardaïa", "Trajet autoroutier vers la ville thermale de Zelfana"),
                ScheduleParagraph("z_p3", "08:30", "الوصول إلى زلفانة", "استقبال في المركز الحموي", "Arrivée à la station de Zelfana", "Accueil à l'établissement thermal"),
                ScheduleParagraph("z_p4", "08:30 – 09:30", "استقبال واستراحة وشرب الشاي الصحراوي", "جلسة استرخاء والتعرف على الخصائص العلاجية للمياه الكبريتية الحارة", "Pause thé saharien et exposé santé", "Dégustation de thé à la menthe et présentation des vertus thérapeutiques des eaux thermales"),
                ScheduleParagraph("z_p5", "09:30 – 11:45", "الحمام المعدني والاستجمام والراحة", "الاستفادة من مياه المسابح الحموية الطبيعية المعروفة بفوائدها الصحية والعضلية وتخفيف التعب", "Bains thermaux chauds et relaxation", "Séance de baignade vivifiante dans les bassins d'eaux minérales chaudes (41,5°C) soulageant la fatigue musculaire"),
                ScheduleParagraph("z_p6", "11:45", "العودة إلى غرداية", "الانطلاق بالحافلة", "Départ pour le retour", "Départ en autocar vers Ghardaïa"),
                ScheduleParagraph("z_p7", "13:00", "الوصول إلى مقر الإقامة", "تناول وجبة الغداء وقضاء بقية اليوم في راحة تامة", "Arrivée à Oued Nechou", "Déjeuner et temps de repos complet après la cure thermale")
            ),
            justification = "اختيرت زلفانة لتكون المحطة الختامية للبرنامج السياحي والترفيهي، بحيث تختلف طبيعتها عن الرحلتين السابقتين. فبعد التعرف على المدينة والتراث والذاكرة الوطنية والواحات، تمنح هذه الرحلة الوفد مساحة أكبر للراحة والاستجمام، بما يساعد على اختتام الجانب الترفيهي من الإقامة في أجواء هادئة.",
            justificationFr = "Après les découvertes culturelles et historiques, cette étape thermale offre un moment privilégié de bien-être physique et de détente musculaire avant les épreuves finales et la clôture du stage.",
            culturalHighlights = listOf("المحطة الحموية زلفانة", "المياه المعدنية الكبريتية الطبيعية", "السياحة العلاجية والاسترخاء في الصحراء"),
            culturalHighlightsFr = listOf("Station thermale réputée de Zelfana", "Source d'eau chaude minérale naturelle (41,5°C)", "Thermalisme saharien & relaxation corporelle")
        ),

        // Folklore Evening 3: El Dandoun
        ProgramDay(
            id = "day_folklore_3",
            dayNumber = 8,
            dateString = "الجمعة 13 نوفمبر 2026",
            dateStringFr = "Vendredi 13 Novembre 2026",
            gregorianDate = "2026-11-13",
            title = "الأمسية الفلكلورية: فرقة «الدندون» الشعبية",
            titleFr = "3ème Soirée Artistique : Troupe Folklorique « El Dandoun »",
            shortSubtitle = "الأسبوع السادس – مساءً – إيقاعات الدندون الأصيلة",
            shortSubtitleFr = "6ème semaine – Grands tambours et chants du Sud",
            location = "غرداية",
            locationFr = "Ghardaïa",
            timeRange = "19:30 إلى 22:00",
            timeRangeFr = "19h30 à 22h00",
            category = EventCategory.FOLKLORE,
            illustrationRes = R.drawable.ic_guide_folklore,
            paragraphs = listOf(
                ScheduleParagraph("dan_p1", "19:30 – 19:45", "افتتاح الأمسية الفنية", "الترحيب بالحضور وتقديم لمحة عن إيقاعات الدندون ومكانتها في الاحتفالات الشعبية بالجنوب", "Ouverture de la veillée", "Présentation de la tradition percussive de l'El Dandoun"),
                ScheduleParagraph("dan_p2", "19:45 – 22:00", "العرض الفني الحماسي لفرقة الدندون", "استعراضات الطبول الكبيرة والرقصات الفلكلورية الصحراوية والأناشيد الحماسية", "Grand concert de tambours et chants", "Chorégraphies entraînantes aux sons des puissants tambours de fête sahariens"),
                ScheduleParagraph("dan_p3", "22:00", "صور جماعية وتكريم رمزي", "أخذ صور ختامية مع أعضاء الفرقة والوفد", "Photos d'adieu artistiques", "Dernières photos festives marquant la fin du cycle des soirées culturelles")
            ),
            justification = "تتويج السهرات الفنية بالتعريف بنمط تراثي ثالث ذو خصوصية إيقاعية مميزة 'الدندون'، للاحتفاء بقرب انتهاء الدورة التكوينية وإضفاء جو من البهجة والمودة الصادقة بين الجميع.",
            justificationFr = "Apothéose des soirées culturelles mettant à l'honneur un troisième registre folklorique authentique du Sud algérien, célébrant la réussite du parcours des stagiaires.",
            culturalHighlights = listOf("إيقاع الدندون الصحراوي", "الفولكلور الشعبي الجنوبي", "الترابط الثقافي الإفريقي"),
            culturalHighlightsFr = listOf("Rythme El Dandoun du grand Sud", "Polyphonies traditionnelles", "Fraternité saharo-sahélienne")
        ),

        // Official Closing Ceremony
        ProgramDay(
            id = "day_closing_ceremony",
            dayNumber = 9,
            dateString = "الثلاثاء 17 نوفمبر 2026",
            dateStringFr = "Mardi 17 Novembre 2026",
            gregorianDate = "2026-11-17",
            title = "الحفل الختامي: مراسم الاختتام الرسمي وتوزيع الشهادات",
            titleFr = "Cérémonie Officielle de Clôture & Remise des Diplômes",
            shortSubtitle = "معهد التعليم المهني بواد نشو – تحت إشراف السيد والي الولاية",
            shortSubtitleFr = "Institut IEP Oued Nechou – Sous le patronage du Wali",
            location = "معهد التعليم المهني بواد نشو – غرداية",
            locationFr = "Institut d'Enseignement Professionnel – Oued Nechou",
            timeRange = "مراسم رسمية بروتوكولية",
            timeRangeFr = "Matinée solennelle et protocolaire",
            category = EventCategory.CEREMONY,
            illustrationRes = R.drawable.ic_guide_ceremony,
            paragraphs = listOf(
                ScheduleParagraph(
                    "cer_p1",
                    "09:30",
                    "الاستقبال والافتتاح الرسمي",
                    "عزف النشيدين الوطنيين للجمهورية الجزائرية الديمقراطية الشعبية وجمهورية النيجر الشقيقة.",
                    "Ouverture solennelle et hymnes nationaux",
                    "Entonnade des hymnes nationaux de la République Algérienne et de la République du Niger."
                ),
                ScheduleParagraph(
                    "cer_p2",
                    "10:00",
                    "كلمة السيد مدير مركز التكوين المهني",
                    "عرض الحصيلة البيداغوجية والتقنية لفترة التكوين والرسكلة وتثمين جهود المتكونين والمؤطرين.",
                    "Allocution du Directeur du Centre de Formation",
                    "Bilan pédagogique et technique de la session et félicitations aux stagiaires."
                ),
                ScheduleParagraph(
                    "cer_p3",
                    "10:15",
                    "كلمة السيد مدير التكوين والتعليم المهنيين للولاية",
                    "تقديم حصيلة الدورة ومسار التعاون المشترك بين قطاع التكوين ودولة النيجر الشقيقة.",
                    "Allocution du Directeur de Wilaya de la Formation",
                    "Synthèse du partenariat bilatéral et des compétences professionnelles transférées."
                ),
                ScheduleParagraph(
                    "cer_p4",
                    "10:30",
                    "كلمة السيد ممثل شركة الطاقات المتجددة (SKTM)",
                    "تثمين الشراكة التقنية الميدانية ومستوى المهارات المكتسبة في تركيب ألواح الطاقة الشمسية وكهرباء المعمار.",
                    "Allocution du Représentant de l'entreprise SKTM",
                    "Valorisation des compétences pratiques acquises en énergie solaire photovoltaïque et électricité du bâtiment."
                ),
                ScheduleParagraph(
                    "cer_p5",
                    "10:45",
                    "كلمة السيد ممثل وفد جمهورية النيجر الشقيقة",
                    "التعبير عن عميق الشكر والامتنان للدولة الجزائرية وقيادتها وسكان غرداية على حسن الاستقبال والضيافة والتأطير العالي.",
                    "Allocution du Chef de délégation de la République du Niger",
                    "Témoignage de gratitude envers l'État algérien, les autorités et le peuple pour l'accueil exemplaire."
                ),
                ScheduleParagraph(
                    "cer_p6",
                    "11:00",
                    "الكلمة الختامية والتوجيهية للسيد والي ولاية غرداية",
                    "الإشادة بنجاح المبادرة الرئاسية، وإبراز البعد الاستراتيجي لعلاقات التعاون والتضامن الإفريقي، والإعلان الرسمي عن اختتام الدورة.",
                    "Discours de clôture de Monsieur le Wali de Ghardaïa",
                    "Éloge de l'initiative présidentielle, rappel des liens historiques de solidarité africaine et clôture officielle."
                ),
                ScheduleParagraph(
                    "cer_p7",
                    "11:30",
                    "مراسم تسليم الشهادات والتكريمات الرسمية",
                    "إشراف السيد الوالي والمديرين التنفيذيين على توزيع شهادات نهاية التكوين والرسكلة على المشاركين الـ 39، وتكريم المؤطرين والقطاعات المساهمة، تليها مأدبة شرفية.",
                    "Remise officielle des diplômes et collation d'honneur",
                    "Remise des attestations de qualification aux 39 stagiaires par le Wali et les directeurs exécutifs, suivie d'un banquet d'honneur."
                )
            ),
            justification = "يُسدل الستار على فعاليات الدورة التكوينية بتنظيم مراسم رسمية تحت الرعاية والإشراف الفعلي للسيد والي ولاية غرداية، وبحضور المديرين التنفيذيين للمصالح الولائية الشريكة (الثقافة، السياحة، الشباب والرياضة، الصحة، الحماية المدنية، النقل)، لتتويج كفاح ومثابرة المتكونين وتجسيد عمق الروابط الإفريقية الأخوية.",
            justificationFr = "Consécration solennelle sous le haut patronage du Wali de Ghardaïa, couronnant avec éclat l'effort assidu des 39 lauréats nigériens et réaffirmant l'engagement de coopération solidaire entre l'Algérie et le Niger.",
            culturalHighlights = listOf("التضامن والتعاون الإفريقي الجزائري النيجيري", "شهادات الكفاءة المهنية المعتمدة", "الشراكة مع شركة SKTM للطاقات المتجددة"),
            culturalHighlightsFr = listOf("Coopération stratégique Algérie-Niger", "Diplômes de qualification reconnus", "Partenariat technique avec SKTM Énergies Renouvelables")
        ),

        // Departure Day
        ProgramDay(
            id = "day_departure",
            dayNumber = 10,
            dateString = "الأربعاء 18 نوفمبر 2026",
            dateStringFr = "Mercredi 18 Novembre 2026",
            gregorianDate = "2026-11-18",
            title = "مرحلة المغادرة ومرافقة الرعايا إلى مطار مفدي زكرياء",
            titleFr = "Départ de la Délégation et Accompagnement à l'Aéroport",
            shortSubtitle = "التوديع الرسمي وتسهيل إجراءات السفر والعودة الميمونة",
            shortSubtitleFr = "Adieux officiels & Formalités de voyage retour",
            location = "مطار مفدي زكرياء الدولي – غرداية",
            locationFr = "Aéroport International Moufdi Zakaria – Ghardaïa",
            timeRange = "حسب توقيت إقلاع الطائرة",
            timeRangeFr = "Selon le plan de vol officiel",
            category = EventCategory.WELCOME,
            illustrationRes = R.drawable.ic_guide_airport,
            paragraphs = listOf(
                ScheduleParagraph(
                    "dep_p1",
                    "صباحاً",
                    "الانطلاق والتأمين من مقر الإقامة",
                    "تسخير حافلة مريحة ومجهزة لنقل الرعايا وأمتعتهم مباشرة نحو مطار مفدي زكريا تحت مرافقة فريق تأطيري مكلّف بالسهر على راحة الوفد وتوفير متطلبات السفر.",
                    "Départ sécurisé depuis le centre d'hébergement",
                    "Mise à disposition d'autocars grand confort pour le transport des passagers et des bagages vers l'aéroport sous escorte attentive."
                ),
                ScheduleParagraph(
                    "dep_p2",
                    "بالمطار",
                    "الاستقبال وتسهيل العبور والمطابقة الأمنية",
                    "استقبال الوفد على مستوى المطار وتسهيل كافة إجراءات العبور وشحن الأمتعة والمطابقة الأمنية بالتنسيق مع شرطة الحدود والجمارك ومصالح المطار.",
                    "Facilitation des formalités d'enregistrement",
                    "Prise en charge aéroportuaire prioritaire en coordination avec la police aux frontières et les douanes."
                ),
                ScheduleParagraph(
                    "dep_p3",
                    "قبل الصعود",
                    "وقفة توديع رسمية بحضور ممثلي المديرية",
                    "تنظيم وقفة توديع رسمية بحضور ممثلي مديرية التكوين المهني وشركاء الدورة؛ تخللها عبارات الشكر والتمنيات بعودة ميمونة ومسار مهني موفق.",
                    "Cérémonie d'adieu et souhaits de bon retour",
                    "Échanges émouvants d'amitié et souhaits de plein succès professionnel au Niger."
                ),
                ScheduleParagraph(
                    "dep_p4",
                    "البهو الشرفي",
                    "التوثيق والصورة التذكارية الجامعة",
                    "أخذ صورة تذكارية جماعية تضم الرعايا النيجريين مع الطاقم المؤطر والمرافق في البهو الشرفي للمطار، تخليداً لمخرجات الدورة وتجسيداً للروابط الأخوية التي جمعت الطرفين.",
                    "Photo d'honneur historique au salon VIP",
                    "Grande prise de vue collective réunissant stagiaires et encadrants au salon d'honneur pour immortaliser ce séjour inoubliable."
                )
            ),
            justification = "وبذلك لا يكون الجانب السياحي مجرد رحلات ترفيهية، وإنما جزءاً من برنامج استقبال وتبادل ثقافي وتعريف بالجزائر، يرافق برنامج التكوين والرسكلة ويحافظ في الوقت نفسه على راحة الوفد وعدم التأثير على سير التكوين.",
            justificationFr = "Le volet touristique et récréatif aura constitué bien plus qu'un simple divertissement : il aura incarné un puissant trait d'union fraternel, accompagnant harmonieusement la montée en compétences des stagiaires.",
            culturalHighlights = listOf("حسن الوداع والتوديع المشرف", "صورة تذكارية تاريخية بالبهو الشرفي", "استمرارية التواصل والتعاون المهني"),
            culturalHighlightsFr = listOf("Adieux chaleureux et fraternels", "Photo historique au salon d'honneur", "Pérennisation du réseau professionnel")
        )
    )

    // Friday Prayer Program (Weekly recurring)
    val fridayPrayerProgram = ProgramDay(
        id = "program_friday_prayer",
        dayNumber = -1,
        dateString = "كل يوم جمعة طيلة فترة الدورة (45 يوماً)",
        dateStringFr = "Chaque Vendredi durant les 45 jours",
        gregorianDate = "أسبوعياً",
        title = "رابعاً: أداء صلاة الجمعة بمسجد «عقبة بن نافع»",
        titleFr = "Prière du Vendredi à la Mosquée « Oqba Ibn Nafi »",
        shortSubtitle = "بوهراوة العليا – غرداية",
        shortSubtitleFr = "Bouhraoua El-Alia – Ghardaïa",
        location = "مسجد عقبة بن نافع – حي بوهراوة العليا – ولاية غرداية",
        locationFr = "Mosquée Oqba Ibn Nafi – Quartier Bouhraoua – Ghardaïa",
        timeRange = "12:15 إلى 14:15",
        timeRangeFr = "12h15 à 14h15",
        category = EventCategory.RELIGIOUS,
        illustrationRes = R.drawable.ic_guide_mosque,
        paragraphs = listOf(
            ScheduleParagraph("fp_p1", "12:15", "التجمع والانطلاق من مقر الإقامة", "الانطلاق المنظم بالحافلات نحو حي بوهراوة العليا", "Départ en autocar", "Trajet groupé vers la mosquée du quartier Bouhraoua"),
            ScheduleParagraph("fp_p2", "12:30", "الوصول إلى مسجد عقبة بن نافع", "الدخول وأخذ الأماكن داخل صحن المسجد المبارك وسماع القرآن الكريم", "Arrivée à la mosquée", "Accueil spirituel et installation dans l'enceinte de la mosquée"),
            ScheduleParagraph("fp_p3", "12:30 – 13:30", "الاستماع لخطبة الجمعة وأداء الصلاة", "الإنصات لخطبة الجمعة وأداء الفريضة في خشوع وسط جموع المصلين", "Prêche et prière collective", "Écoute du sermon du vendredi et accomplissement de la prière dans la piété et la fraternité"),
            ScheduleParagraph("fp_p4", "13:30 – 14:00", "استراحة قصيرة وتعارف مع إمام المسجد والمصلين", "تبادل التحيات والتعارف الأخوي مع فضيلة إمام المسجد وأبناء حي بوهراوة في مشهد يجسد روح الأخوة الإسلامية", "Rencontre fraternelle et échanges", "Salutations cordiales avec l'Imam et les fidèles du quartier"),
            ScheduleParagraph("fp_p5", "14:15", "الانطلاق للعودة إلى مقر الإقامة", "العودة لتناول وجبة الغداء العائلي بواد نشو", "Retour à la résidence", "Retour en autocar pour le déjeuner convivial du vendredi")
        ),
        justification = "تعزيزاً للروابط الروحية والأخوية التي تجمع بين الشعبين الجزائري والنيجري، وتلبية للجانب الديني للوفد، تمت برمجة أداء صلاة الجمعة بمسجد 'عقبة بن نافع' الكائن بحي بوهراوة العليا بولاية غرداية، والتي تهدف هذه المحطة إلى تمكين الوفد من أداء شعائرهم في أجواء إيمانية، ومشاركة إخوانهم من سكان المنطقة، مما يعكس قيم التلاحم، حسن الضيافة، والأخوة الإسلامية.",
        justificationFr = "Pour nourrir les liens spirituels et fraternels entre les deux peuples musulmans, cette pause hebdomadaire permet aux membres de la délégation de pratiquer leur culte dans une ambiance chaleureuse auprès des habitants locaux.",
        culturalHighlights = listOf("مسجد عقبة بن نافع التاريخي", "الأخوة الإسلامية والتضامن الإيماني", "كرم وضيافة سكان حي بوهراوة العليا"),
        culturalHighlightsFr = listOf("Mosquée historique Oqba Ibn Nafi", "Fraternité spirituelle islamique", "Hospitalité chaleureuse des habitants de Bouhraoua")
    )

    // Complete list of all 39 participants from page 14 (Omitted phone and passport numbers for privacy)
    val participantsList: List<Participant> = listOf(
        Participant(1, "ABOUBACAR MOUDASSIROU", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(2, "ADAMOU OUMAROU MANSOUR", "M", "كهرباء المعمار"),
        Participant(3, "AGUEDJOU OGOUGARA M AGUEDJOU LIWAN VINCENT", "M", "كهرباء المعمار"),
        Participant(4, "ALI HAROUNA SEYDI", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(5, "ALIO DAOUDA MANIROU", "M", "كهرباء المعمار"),
        Participant(6, "ALMOUSTAPHA DOUDOU SALOU", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(7, "AMADOU SEYNI ALMIYAOU", "M", "كهرباء المعمار"),
        Participant(8, "AMANI GARBA RABE", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(9, "AMINOU MAMANE ADAM", "M", "كهرباء المعمار"),
        Participant(10, "AZIZOU HAROUNA ZEINAB", "F", "كهرباء المعمار"),
        Participant(11, "BOUBACAR TCHEMOGO FATAHOU MAYAKI", "M", "كهرباء المعمار"),
        Participant(12, "CHAMSOU SANI GARBA", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(13, "GANDA SINKA RAKIA", "F", "كهرباء المعمار"),
        Participant(14, "HAMIDOU ABDOU ADAMOU", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(15, "HAROUNA MALIKI MALIK BOUBACAR", "M", "كهرباء المعمار"),
        Participant(16, "IBRAHIM ABOUBACAR YAHOUZA", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(17, "IBRAHIM BOUBACAR ISSA", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(18, "IBRAHIM TAHER ATTOUMANE", "M", "كهرباء المعمار"),
        Participant(19, "IBRAHIMA GUIMBA BACHIROU", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(20, "IDRISSA ALFARI SAMBA ABDOUL RACHID", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(21, "INOUSSA LIFIDI RAYANE", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(22, "ISMAEL ALI BARA", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(23, "ISSA MAMANE SANI", "M", "كهرباء المعمار"),
        Participant(24, "ISSAKA CHAIBOU BOUCARY", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(25, "ISSOUFOU ABOUBACAR MAHAZOU", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(26, "ISSOUFOU BOUBACAR ZAYID", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(27, "KIARI MELE KAROU OUSMANE", "M", "كهرباء المعمار"),
        Participant(28, "LEON ARGI YACOUBA", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(29, "MAHAMADOU ISSAKA SAIDOU", "M", "كهرباء المعمار"),
        Participant(30, "MAHAMADOU KANONI ISMAEL", "M", "كهرباء المعمار"),
        Participant(31, "MAHAMAN NAFIOU S FAROUK", "M", "كهرباء المعمار"),
        Participant(32, "MAMADOU TOURA BOUNA BARMARAM", "F", "كهرباء المعمار"),
        Participant(33, "OUKASSI HALIDOU DIABRI", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(34, "RABI GARBA HAMIDOU", "F", "كهرباء المعمار"),
        Participant(35, "RABIOU CHERIF ABDOUR RAHIM", "M", "تركيب ألواح الطاقة الشمسية"),
        Participant(36, "RAISSA IBRAHIM AMADOU", "F", "كهرباء المعمار"),
        Participant(37, "SALOUHOU SOULEY DJIBO", "M", "كهرباء المعمار"),
        Participant(38, "SOUMAILA KINASSA ABDOULAYE", "M", "كهرباء المعمار"),
        Participant(39, "YOUNOUSSA HAINIKOYE NADIA", "F", "كهرباء المعمار")
    )

    // Cultural Landmarks for Visual Guide Gallery (With distinct tailored illustrations)
    val culturalLandmarks = listOf(
        CulturalLandmark(
            id = "lm_ghardaia",
            name = "وادي ميزاب وقصر غرداية العتيق",
            nameFr = "Vallée du M'Zab et Vieux Ksar de Ghardaïa",
            location = "وسط غرداية",
            locationFr = "Centre de Ghardaïa",
            summary = "تحفة عمرانية مصنفة ضمن التراث العالمي لليونسكو منذ سنة 1982، نموذج عالمي للتخطيط البيئي والتضامن الإنساني.",
            summaryFr = "Joyau architectural classé au patrimoine mondial de l'UNESCO depuis 1982, modèle universel d'urbanisme bioclimatique et de solidarité humaine.",
            detailedHistory = "تأسس قصر غرداية في القرن الحادي عشر الميلادي على تلة هرمية يتوسطها المسجد العتيق بمئذنته البارزة. يتميز النسيج العمراني بشوارعه الملتوية ومنازله المتلاصقة التي توفر عزلاً حرارياً طبيعياً ضد حرارة الصيف وبرودة الشتاء الصحراوي.",
            detailedHistoryFr = "Fondé au XIe siècle sur une colline pyramidale surmontée de son minaret emblématique, le ksar de Ghardaïa présente un tissu urbain dense et harmonieux assurant une isolation thermique naturelle remarquable.",
            architecturalFeatures = listOf(
                "المئذنة الهرمية الفريدة كنقطة مراقبة وتجمع",
                "نظام التهوية والإضاءة الطبيعية في السقف (التجمي)",
                "استعمال المواد المحلية: الجص، الطين، وسعف وخشب النخيل",
                "المحافظة الصارمة على خصوصية الجوار ومقاييس البناء المتوازنة"
            ),
            architecturalFeaturesFr = listOf(
                "Minaret pyramidal sentinelle dominant toute la vallée",
                "Système d'ouverture zénithale 'Tadjemmi' pour l'aération et la lumière",
                "Matériaux locaux : chaux (timchent), argile et bois de palmier",
                "Règles strictes de mitoyenneté et respect de l'intimité familiale"
            ),
            tipsForVisitors = "ارتداء أحذية مريحة للمشي في الأزقة المتدرجة، واحترام التقاليد المحلية والتصوير بإذن السكان.",
            tipsForVisitorsFr = "Chaussures de marche recommandées pour les venelles pavées ; respect des coutumes locales et discrétion pour les photos.",
            drawableRes = R.drawable.ic_guide_ghardaia
        ),
        CulturalLandmark(
            id = "lm_tafilalt",
            name = "قصر تافيلالت البيئي النموذجي",
            nameFr = "Ksar Écologique Modèle de Tafilalet",
            location = "بني يزقن – غرداية",
            locationFr = "Beni Isguen – Ghardaïa",
            summary = "أول قصر بيئي مستدام في الجزائر وإفريقيا يجمع بين أصالة التراث المعماري وتقنيات العمارة الخضراء المعاصرة.",
            summaryFr = "Première éco-cité durable d'Algérie et d'Afrique, alliant l'ingéniosité architecturale mozabite aux technologies vertes modernes.",
            detailedHistory = "مشروع مجتمعي رائد أقيم على هضبة صخرية لاستيعاب التوسع السكاني دون المساس بواحات النخيل الثمينة. فاز بجائزة المدينة المستدامة الدولية بمؤتمر المناخ COP22 بالمغرب سنة 2016.",
            detailedHistoryFr = "Érigée sur un plateau aride pour préserver la palmeraie nourricière, cette cité communautaire a remporté le Prix International de la Ville Durable lors de la COP22 en 2016.",
            architecturalFeatures = listOf(
                "استخدام الحجارة والجبس المحلي في البناء المعزول حرارياً",
                "محطة معالجة مياه الصرف بنباتات القصب وتدويرها لسقي الغابة",
                "طاقة شمسية لإنارة الفضاءات العامة وحديقة نباتية بيئية",
                "حديقة حيوانات صحراوية وبرامج تربوية للأطفال والشباب"
            ),
            architecturalFeaturesFr = listOf(
                "Constructions en pierres sèches et plâtre local à haute inertie thermique",
                "Station de phyto-épuration par les roseaux pour le recyclage des eaux usées",
                "Éclairage public solaire photovoltaïque et micro-forêt éducative",
                "Parc zoologique et botanique saharien participatif"
            ),
            tipsForVisitors = "ملاحظة التناغم المذهل بين الحداثة والتقاليد، والاستماع لشرح نظام التدوير البيئي للقصر.",
            tipsForVisitorsFr = "À observer : la parfaite harmonie entre solutions traditionnelles en pierre et innovations solaires modernes.",
            drawableRes = R.drawable.ic_guide_tafilalet
        ),
        CulturalLandmark(
            id = "lm_beni_isguen",
            name = "قصر بني يزقن وسورها المحصن",
            nameFr = "Ksar Fortifié de Beni Isguen & Tour Boulila",
            location = "بني يزقن",
            locationFr = "Beni Isguen",
            summary = "المدينة العلمية المحصنة، وأحد أجمل القصور التاريخية بأسوارها الدفاعية وبرج بوليلة الشهير.",
            summaryFr = "Cité religieuse et lettrée réputée pour sa propreté exemplaire, ses hauts remparts intacts et sa tour de guet Boulila.",
            detailedHistory = "تأسس قصر بني يزقن في القرن الرابع عشر، ويشتهر بسوق لالة عشو وسوق المزاد العلني العريق بعد صلاة العصر، حيث تباع الزرابي والتحف التراثية بأعلى درجات الأمانة.",
            detailedHistoryFr = "Fondé au XIVe siècle, le ksar perpétue la vente aux enchères traditionnelle de la criée chaque après-midi sur la place Lalla Achou, dans un climat d'intégrité remarquable.",
            architecturalFeatures = listOf(
                "السور الدفاعي التاريخي الذي يحيط بالقصر كاملاً",
                "برج بوليلة في أعلى قمة القصر لمراقبة الوافدين والأخطار",
                "نظام اجتماعي وقيمي صارم يحافظ على نظافة الأزقة وهدوء السكان"
            ),
            architecturalFeaturesFr = listOf(
                "Ceinture continue de remparts défensifs crénelés",
                "Tour de guet 'Boulila' dominant le sommet de la cité",
                "Code civique et moral rigoureux préservant la propreté et la tranquillité"
            ),
            tipsForVisitors = "الالتزام باللباس المحتشم والتمتع بسحر الهدوء والسكينة داخل أزقة القصر.",
            tipsForVisitorsFr = "Tenue décente requise ; savourez le calme et la sérénité exceptionnelle des ruelles médiévales.",
            drawableRes = R.drawable.ic_guide_beni_isguen
        ),
        CulturalLandmark(
            id = "lm_sidi_abbaz",
            name = "معلم سيدي عباز وإطلالة ساحة الوئام",
            nameFr = "Belvédère de Sidi Abbaz & Esplanade de la Concorde",
            location = "مرتفعات بني يزقن",
            locationFr = "Hauteurs de Beni Isguen",
            summary = "المطل البانورامي الأكثر شهرة وإبهاراً لمشاهدة واحات وقصور وادي ميزاب المتلألئة عند الغروب.",
            summaryFr = "Le plus spectaculaire belvédère naturel offrant une perspective à 360 degrés sur les palmeraies et les ksour millénaires.",
            detailedHistory = "معلم عريق يستند إلى صخرة تشرف على مسار الوادي، يتيح للزائرين معاينة عبقرية توزيع الواحات والمباني وتوازنها مع التضاريس الصخرية المحيطة.",
            detailedHistoryFr = "Point culminant dominant les vallons et les méandres de l'oued, illustrant la maîtrise de l'espace désertique par les bâtisseurs mozabites.",
            architecturalFeatures = listOf(
                "قوس ومعلم معماري تراثي يوثق تاريخ المنطقة",
                "شرفة بانورامية فسيحة ومجهزة (ساحة الوئام) للتأمل والاستراحة",
                "إطلالة شاملة على وادي ميزاب وتلاله الحجرية"
            ),
            architecturalFeaturesFr = listOf(
                "Monument à voûtes traditionnelles encadrant la vue",
                "Esplanade aménagée 'Place de la Concorde' propice aux photos de groupe",
                "Panorama grandiose sur le ruban verdoyant de la palmeraie"
            ),
            tipsForVisitors = "أفضل توقيت للزيارة هو قبيل غروب الشمس لالتقاط أجمل الصور التذكارية.",
            tipsForVisitorsFr = "Moment idéal : au crépuscule, lorsque la lumière dorée embrase les façades ocres des ksour.",
            drawableRes = R.drawable.ic_guide_sidi_abbaz
        ),
        CulturalLandmark(
            id = "lm_metlili",
            name = "متليلي الشعانبة والقصر القديم ومتحف المجاهد",
            nameFr = "Metlili des Chaamba, Vieux Ksar & Musée du Moudjahid",
            location = "بلدية متليلي (30 كم جنوب غرداية)",
            locationFr = "Metlili (30 km au sud de Ghardaïa)",
            summary = "عاصمة قبائل الشعانبة الأماجد، قلعة التاريخ الجهادي والمقاومة الشعبية ضد الاستعمار الفرنسي.",
            summaryFr = "Fief historique des cavaliers nomades Chaamba, haut-lieu de la résistance populaire et de la Révolution de Libération nationale.",
            detailedHistory = "تحتضن متليلي قصرها العتيق المشيد على ربوة تحرس وادي متليلي، وتضم متحف المجاهد الزاخر بوثائق وبطولات ثورة أول نوفمبر والشهداء الذين رووا بدمائهم الزكية ثرى الوطن.",
            detailedHistoryFr = "Metlili abrite son ksar perché et le Musée du Moudjahid qui conserve les reliques héroïques, armes et étendards de la guerre d'indépendance algérienne.",
            architecturalFeatures = listOf(
                "الأسوار والأبراج الدفاعية للقصر القديم وسوق متليلي التقليدي",
                "متحف المجاهد بما يحويه من أسلحة تاريخية وأعلام الثورة وسجلات الشهداء",
                "مقبرة الشهداء التي تخلد التضحيات البطولية للشعب الجزائري"
            ),
            architecturalFeaturesFr = listOf(
                "Remparts et bastions crénelés du ksar bédouin",
                "Musée d'histoire militaire et registres des martyrs de la liberté",
                "Cimetière des Chouhada honorant la mémoire des combattants"
            ),
            tipsForVisitors = "فرصة رائعة لفهم البعد التحرري الإفريقي وكيف ساندت الجزائر قضايا التحرر في القارة الإفريقية.",
            tipsForVisitorsFr = "Une étape émouvante pour mesurer la solidarité panafricaine et le prix de la souveraineté nationale.",
            drawableRes = R.drawable.ic_guide_metlili
        ),
        CulturalLandmark(
            id = "lm_sebseb",
            name = "واحة سبسب ومنتجع الهدار السياحي",
            nameFr = "Oasis de Sebseb & Complexe Touristique Al-Haddar",
            location = "سبسب (جنوب متليلي)",
            locationFr = "Sebseb (au sud de Metlili)",
            summary = "واحة غناء في قلب الكثبان الرملية الذهبية، ونموذج رائع للزراعة الصحراوية والاستجمام السياحي.",
            summaryFr = "Oasis luxuriante nichée au pied des dunes blondes, modèle agricole saharien et pôle d'attraction touristique.",
            detailedHistory = "تشتهر سبسب ببساتين النخيل الباسقة وإنتاج أجود أنواع التمور (دقلة نور والغرس)، ويعكس منتجع الهدار الاستثمار السياحي الواعد الذي يجمع بين الترفيه العائلي وسحر الطبيعة الصحراوية.",
            detailedHistoryFr = "Célèbre pour ses dattes renommées et ses puits d'eau douce, Sebseb accueille le complexe Al-Haddar offrant bassins de fraîcheur et détente au milieu des palmiers.",
            architecturalFeatures = listOf(
                "نظام السواقي لتوزيع مياه السقي بدقة بين البساتين",
                "كثبان رملية ساحرة تمتد بمحاذاة النخيل الأخضر",
                "مرافق الإطعام والراحة والاستجمام بمنتجع الهدار"
            ),
            architecturalFeaturesFr = listOf(
                "Réseau traditionnel de séguias irriguant chaque parcelle de palmier",
                "Contraste saisissant entre les dunes dorées et le vert des frondaisons",
                "Infrastructures de loisirs et d'hébergement du complexe Al-Haddar"
            ),
            tipsForVisitors = "تذوق التمور الطازجة وشرب الشاي الصحراوي بالنعناع تحت ظلال النخيل الباسق.",
            tipsForVisitorsFr = "À ne pas manquer : dégustation de dattes fraîches et thé à la menthe sous les palmiers dattiers.",
            drawableRes = R.drawable.ic_guide_oasis
        ),
        CulturalLandmark(
            id = "lm_zelfana",
            name = "حمام زلفانة المعدني الطبيعي",
            nameFr = "Station Thermale Naturelle de Zelfana",
            location = "بلدية زلفانة (65 كم شرق غرداية)",
            locationFr = "Zelfana (65 km à l'Est de Ghardaïa)",
            summary = "واحة استشفائية وحموية ذات شهرة وطنية بمياهها الساخنة المتدفقة من باطن الأرض.",
            summaryFr = "Oasis thermale de réputation nationale, réputée pour ses sources d'eau chaude minérale jaillissant des profondeurs.",
            detailedHistory = "اكتشفت منابع زلفانة الحموية في أواخر أربعينيات القرن الماضي خلال عمليات التنقيب البترولي، لتتحول الواحة إلى قبلة وطنية للسياحة العلاجية والاسترخاء النفسي والبدني.",
            detailedHistoryFr = "Découvertes dans les années 1940, les sources thermales de Zelfana sont devenues un lieu de cure privilégié pour soulager les rhumatismes et le stress.",
            architecturalFeatures = listOf(
                "مياه كبريتية نقية تتدفق بدرجة حرارة تصل إلى 41.5 درجة مئوية",
                "مسابح وحمامات مهيأة للعلاج الطبيعي وتنشيط الدورة الدموية ومفاصل الجسم",
                "واحة خضراء هادئة توفر الراحة والاستجمام لزوارها"
            ),
            architecturalFeaturesFr = listOf(
                "Eaux thermo-minérales sulfurées à 41,5°C aux vertus antalgiques",
                "Piscines et cabines individuelles de balnéothérapie",
                "Palmeraie verdoyante créant un microclimat apaisant en plein Sahara"
            ),
            tipsForVisitors = "تعتبر هذه المحطة مثالية لإزالة التعب وتجديد الحيوية والنشاط للمتدربين.",
            tipsForVisitorsFr = "L'escale bien-être par excellence pour effacer la fatigue physique après les travaux d'atelier.",
            drawableRes = R.drawable.ic_guide_zelfana
        ),
        CulturalLandmark(
            id = "lm_moufdi",
            name = "مرقد شاعر الثورة «مفدي زكرياء»",
            nameFr = "Mausolée du Poète de la Révolution « Moufdi Zakaria »",
            location = "بني يزقن – غرداية",
            locationFr = "Beni Isguen – Ghardaïa",
            summary = "مرقد مؤلف النشيد الوطني الجزائري الخالد 'قسماً'، وأحد أبرز رموز الأدب والنضال المغاربي والإفريقي.",
            summaryFr = "Sépulture du poète national algérien, chantre de la Révolution et auteur des paroles immortelles de l'hymne 'Qassaman'.",
            detailedHistory = "ولد مفدي زكرياء ببني يزقن سنة 1908، ونذر قلمه وشعره للثورة والحرية ووحدة إفريقيا والمغرب العربي. كتب النشيد الوطني الجزائري بدمه وهو في سجن بربروس الاستعماري سنة 1955.",
            detailedHistoryFr = "Né à Beni Isguen en 1908, Moufdi Zakaria consacra son verbe à la libération des peuples africains. Il écrivit les vers de l'hymne national avec son sang sur les murs de la prison de Barberousse en 1955.",
            architecturalFeatures = listOf(
                "قبر بسيط ومهيب يعكس تواضع وأصالة أعلام المنطقة",
                "إطلالة بانورامية تشرف على قصر بني يزقن وسورها التاريخي",
                "رمزية وطنية تعمق الروابط الثقافية والأدبية بين الشعوب"
            ),
            architecturalFeaturesFr = listOf(
                "Monument sobre empreint de dignité et de recueillement",
                "Emplacement dominant les collines et les remparts de son village natal",
                "Symbole éclatant de la lutte anticoloniale et de la fraternité africaine"
            ),
            tipsForVisitors = "الوقوف دقيقة صمت وتأمل في مسيرة الشاعر والنشيد الوطني الذي يلهم الأحرار في العالم.",
            tipsForVisitorsFr = "Un temps d'hommage et de méditation devant l'héritage d'un homme de lettres engagé pour l'émancipation.",
            drawableRes = R.drawable.ic_guide_moufdi
        )
    )

    // Interactive Map Landmarks across Ghardaïa Province (All with distinct tailored illustrations)
    val mapLandmarks = listOf(
        MapLandmark(
            id = "map_oued_nechou_male",
            name = "مركز التكوين المهني واد نشو (ذكور)",
            nameFr = "CFPA Oued Nechou (Hébergement Garçons)",
            subtitle = "مقر إقامة المتكونين الذكور وانطلاق الرحلات",
            subtitleFr = "Résidence des stagiaires masculins & Départ des sorties",
            category = "مقر الإقامة والتكوين",
            categoryFr = "Hébergement & Formation",
            latitude = 32.5532,
            longitude = 3.6521,
            description = "مركز التكوين المهني والتمهين بواد نشو (شمال غرداية)، المقر الرئيسي لإقامة المتكونين الذكور ونقطة التجمع والانطلاق لكافة الرحلات السياحية.",
            descriptionFr = "Centre de formation professionnelle d'Oued Nechou, lieu de vie des stagiaires hommes et point de départ des circuits touristiques.",
            associatedDayId = "day_reception_2",
            drawableRes = R.drawable.ic_guide_residence,
            markerColorHex = 0xFF00796B
        ),
        MapLandmark(
            id = "map_oued_nechou_female",
            name = "معهد التعليم المهني واد نشو (إناث)",
            nameFr = "IEP Oued Nechou (Hébergement Filles & Clôture)",
            subtitle = "مقر إقامة الإناث وموقع الحفل الختامي",
            subtitleFr = "Résidence des stagiaires féminines & Cérémonie finale",
            category = "مقر الإقامة والحفل الختامي",
            categoryFr = "Hébergement & Cérémonie",
            latitude = 32.5510,
            longitude = 3.6540,
            description = "معهد التعليم المهني المتطور بواد نشو، مخصص لإقامة المتكونات الإناث ومقر احتضان الحفل الختامي ومراسم توزيع الشهادات تحت إشراف والي الولاية.",
            descriptionFr = "Institut spécialisé d'Oued Nechou accueillant les stagiaires femmes et hôte de la cérémonie solennelle de clôture.",
            associatedDayId = "day_closing_ceremony",
            drawableRes = R.drawable.ic_guide_ceremony,
            markerColorHex = 0xFF283593
        ),
        MapLandmark(
            id = "map_airport",
            name = "مطار مفدي زكرياء الدولي (نوميرات)",
            nameFr = "Aéroport International Moufdi Zakaria",
            subtitle = "نقطة الوصول، الاستقبال والتوديع الرسمي",
            subtitleFr = "Accueil officiel à l'arrivée & Adieux solennels",
            category = "الاستقبال والتوديع",
            categoryFr = "Accueil & Départ",
            latitude = 32.3842,
            longitude = 3.7944,
            description = "مطار غرداية الدولي، موقع استقبال الوفد النيجيري الشقيق فور وصول طائرتهم (30 سبتمبر) وموقع وقفة التوديع وأخذ الصورة التذكارية بالبهو الشرفي (18 نوفمبر).",
            descriptionFr = "Plateforme aéroportuaire internationale de Ghardaïa, théâtre de l'accueil de la délégation le 30 septembre et des adieux officiels le 18 novembre.",
            associatedDayId = "day_reception_1",
            drawableRes = R.drawable.ic_guide_airport,
            markerColorHex = 0xFF00838F
        ),
        MapLandmark(
            id = "map_ghardaia_souk",
            name = "قصر غرداية وساحة السوق العتيق",
            nameFr = "Ksar de Ghardaïa & Grand Souk",
            subtitle = "وسط المدينة، السوق التراثي والحرف اليدوية",
            subtitleFr = "Cœur historique, artisanat et tapis mozabites",
            category = "الرحلة الأولى (10 أكتوبر)",
            categoryFr = "1ère Excursion (10 Oct)",
            latitude = 32.4909,
            longitude = 3.6738,
            description = "الساحة التاريخية المستطيلة لسوق غرداية العريق، دكاكين الزرابي الميزابية المصنفة، الفخار، التمور، والأزقة التقليدية المتدرجة.",
            descriptionFr = "Place historique du marché traditionnel avec ses arcades, galeries d'artisanat, tissages en laine et poteries.",
            associatedDayId = "day_excursion_1",
            drawableRes = R.drawable.ic_guide_ghardaia,
            markerColorHex = 0xFFD84315
        ),
        MapLandmark(
            id = "map_beni_isguen",
            name = "قصر بني يزقن وسورها التاريخي",
            nameFr = "Ksar de Beni Isguen & Remparts",
            subtitle = "القصر المحصن والنظام الاجتماعي الفريد",
            subtitleFr = "Cité fortifiée, tour Boulila et marché à la criée",
            category = "الرحلة الأولى (10 أكتوبر)",
            categoryFr = "1ère Excursion (10 Oct)",
            latitude = 32.4740,
            longitude = 3.6890,
            description = "أحد أبرز قصور وادي ميزاب، يتميز بالنظافة الفائقة، الأسوار الحصينة، برج بوليلة التاريخي، وسوق لالة عشو التقليدي بالمزاد العلني.",
            descriptionFr = "L'un des ksour les plus préservés avec ses remparts imposants et son marché aux enchères séculaire.",
            associatedDayId = "day_excursion_1",
            drawableRes = R.drawable.ic_guide_beni_isguen,
            markerColorHex = 0xFFD84315
        ),
        MapLandmark(
            id = "map_sidi_abbaz",
            name = "معلم سيدي عباز وإطلالة بني يزقن",
            nameFr = "Belvédère de Sidi Abbaz & Place de la Concorde",
            subtitle = "الجولة الترحيبية وساحة الوئام",
            subtitleFr = "Panorama d'exception sur la vallée du M'Zab",
            category = "الجولة الترحيبية (02 أكتوبر)",
            categoryFr = "Visite d'accueil (02 Oct)",
            latitude = 32.4780,
            longitude = 3.6930,
            description = "موقع الجولة الترحيبية الأولى للوفد، يمنح نظرة بانورامية شاملة على جمال المعمار الميزابي وواحات النخيل المجاورة.",
            descriptionFr = "Point culminant dominant les méandres de l'oued et la palmeraie, cadre de la première excursion de découverte.",
            associatedDayId = "day_tour_welcome",
            drawableRes = R.drawable.ic_guide_sidi_abbaz,
            markerColorHex = 0xFF00796B
        ),
        MapLandmark(
            id = "map_moufdi_zakaria",
            name = "مرقد شاعر الثورة «مفدي زكرياء»",
            nameFr = "Mausolée du Poète « Moufdi Zakaria »",
            subtitle = "رمزية أدبية ونضالية إفريقية",
            subtitleFr = "Auteur de l'hymne national Qassaman",
            category = "الجولة الترحيبية (02 أكتوبر)",
            categoryFr = "Mémoire & Histoire (02 Oct)",
            latitude = 32.4755,
            longitude = 3.6875,
            description = "مرقد مؤلف النشيد الوطني الجزائري الخالد 'قسماً'، محطة رمزية هامة لإبراز الروابط التاريخية والنضالية التي تجمع شعوب القارة الإفريقية.",
            descriptionFr = "Lieu de recueillement honorant la mémoire du grand poète combattant algérien dont les écrits ont inspiré la liberté.",
            associatedDayId = "day_tour_welcome",
            drawableRes = R.drawable.ic_guide_moufdi,
            markerColorHex = 0xFFC62828
        ),
        MapLandmark(
            id = "map_tafilalet",
            name = "قصر تافيلالت البيئي النموذجي",
            nameFr = "Ksar Écologique de Tafilalet",
            subtitle = "الامتداد العمراني الأخضر المستدام",
            subtitleFr = "Éco-cité solaire primée à la COP22",
            category = "الرحلة الأولى (10 أكتوبر)",
            categoryFr = "1ère Excursion (10 Oct)",
            latitude = 32.4630,
            longitude = 3.6950,
            description = "أول قصر بيئي مستدام في الجزائر، يعتمد على الطاقة الشمسية، تدوير مياه الصرف بالنباتات، واستخدام المواد الترابية المعزولة حرارياً.",
            descriptionFr = "Ville écologique pionnière intégrant panneaux solaires, recyclage végétal des eaux et architecture en pierre locale.",
            associatedDayId = "day_excursion_1",
            drawableRes = R.drawable.ic_guide_tafilalet,
            markerColorHex = 0xFF2E7D32
        ),
        MapLandmark(
            id = "map_mosque_oqba",
            name = "مسجد «عقبة بن نافع» (بوهراوة العليا)",
            nameFr = "Mosquée « Oqba Ibn Nafi » (Bouhraoua)",
            subtitle = "أداء صلاة الجمعة الأسبوعية والتعارف الأخوي",
            subtitleFr = "Prière du vendredi & Communion fraternelle",
            category = "شعائر وروحانيات (كل جمعة)",
            categoryFr = "Culte du Vendredi",
            latitude = 32.5020,
            longitude = 3.6850,
            description = "المسجد المبرمج لأداء شعائر صلاة الجمعة طيلة الـ 45 يوماً، لتعزيز الروابط الروحية والتعارف مع أهالي حي بوهراوة العليا وإمام المسجد.",
            descriptionFr = "Édifice religieux accueillant la délégation chaque vendredi pour la prière et des moments d'échange chaleureux avec les fidèles.",
            associatedDayId = "program_friday_prayer",
            drawableRes = R.drawable.ic_guide_mosque,
            markerColorHex = 0xFF2E7D32
        ),
        MapLandmark(
            id = "map_metlili_museum",
            name = "متحف المجاهد والقصر القديم بمتليلي",
            nameFr = "Ksar de Metlili & Musée du Moudjahid",
            subtitle = "الذاكرة الوطنية والتراث العمراني للشعانبة",
            subtitleFr = "Mémoire de la Révolution & Forteresse nomade",
            category = "الرحلة الثانية (24 أكتوبر)",
            categoryFr = "2ème Excursion (24 Oct)",
            latitude = 32.2710,
            longitude = 3.6260,
            description = "عاصمة قبائل الشعانبة الأماجد، تضم متحف المجاهد الزاخر بتضحيات الثورة التحريرية، مقبرة الشهداء، والقصر القديم بأسواره التاريخية.",
            descriptionFr = "Cité des Chaamba abritant le Musée de la Révolution, le cimetière des martyrs et les remparts du vieux ksar.",
            associatedDayId = "day_excursion_2",
            drawableRes = R.drawable.ic_guide_metlili,
            markerColorHex = 0xFFB71C1C
        ),
        MapLandmark(
            id = "map_sebseb_oasis",
            name = "واحة سبسب ومنتجع الهدار السياحي",
            nameFr = "Oasis de Sebseb & Complexe Al-Haddar",
            subtitle = "واحة النخيل والاستجمام الصحي",
            subtitleFr = "Palmeraie saharienne & Détente aquatique",
            category = "الرحلة الثانية (24 أكتوبر)",
            categoryFr = "2ème Excursion (24 Oct)",
            latitude = 32.1850,
            longitude = 3.5850,
            description = "واحة نخيل خلابة وسط الكثبان الرملية الذهبية ونظام السقي التقليدي، تليها استراحة ممتعة بمنتجع الهدار السياحي للترويح عن الوفد.",
            descriptionFr = "Palmeraie traversée de séguias traditionnelles aux portes des dunes et complexe de villégiature Al-Haddar.",
            associatedDayId = "day_excursion_2",
            drawableRes = R.drawable.ic_guide_oasis,
            markerColorHex = 0xFFEF6C00
        ),
        MapLandmark(
            id = "map_zelfana",
            name = "المحطة الحموية زلفانة (الحمام المعدني)",
            nameFr = "Station Thermale Chaude de Zelfana",
            subtitle = "مياه كبريتية طبيعية واستجمام ختامي",
            subtitleFr = "Eaux minérales à 41,5°C & Remise en forme",
            category = "الرحلة الثالثة (07 نوفمبر)",
            categoryFr = "3ème Excursion (07 Nov)",
            latitude = 32.3950,
            longitude = 4.2250,
            description = "واحة حموية ذات شهرة وطنية بمياهها الكبريتية الحارة المتدفقة (41.5°م)، محطة استجمامية مخصصة للراحة البدنية بعد أسابيع من التكوين.",
            descriptionFr = "Source thermale saharienne réputée offrant des bassins chauds réparateurs pour clore le programme des sorties.",
            associatedDayId = "day_excursion_3",
            drawableRes = R.drawable.ic_guide_zelfana,
            markerColorHex = 0xFF00838F
        )
    )

    // Conversational AI Topics & Quick Prompts for Practice
    val conversationalTopics = listOf(
        ConversationTopic(
            id = "topic_greetings",
            title = "التعارف والتحيات الثنائية",
            titleFr = "Salutations et Présentations",
            description = "تدرب على تبادل التحيات والتعارف بين المشاركين النيجيريين وأهالي غرداية بالعربية والفرنسية",
            samplePrompts = listOf(
                "السلام عليكم، أنا متكون من وفد جمهورية النيجر، سعيد بزيارة غرداية!",
                "Bonjour! Je suis ravi de participer à cette formation à Ghardaïa.",
                "كيف حالكم؟ ما هي أفضل الكلمات الجزائرية للترحيب والتعارف؟",
                "Apprenez-moi les formules de politesse en dialecte algérien."
            )
        ),
        ConversationTopic(
            id = "topic_solar",
            title = "مصطلحات الطاقة الشمسية وكهرباء المعمار",
            titleFr = "Énergie Solaire & Électricité du Bâtiment",
            description = "ممارسة المصطلحات التقنية المستخدمة في الورشات مع مهندسي شركة SKTM ومعهد التكوين",
            samplePrompts = listOf(
                "ما هي المصطلحات الأساسية لتركيب ألواح الطاقة الشمسية بالعربية والفرنسية؟",
                "Quels sont les composants clés d'une installation solaire photovoltaïque?",
                "كيف أشرح طريقة توصيل الأسلاك الكهربائية في كهرباء المعمار بأمان؟",
                "Comment vérifier la tension et l'intensité des onduleurs solaires?"
            )
        ),
        ConversationTopic(
            id = "topic_ghardaia_culture",
            title = "استكشاف تاريخ ومعالم غرداية ووادي ميزاب",
            titleFr = "Histoire & Patrimoine du M'Zab",
            description = "حوار مع المرشد السياحي الذكي عن القصور السبعة، العمارة الطينية، وسوق غرداية",
            samplePrompts = listOf(
                "حدثني عن هندسة قصور وادي ميزاب وسبب تصنيفها في اليونسكو؟",
                "Quelle est l'histoire du poète Moufdi Zakaria, l'auteur de Qassaman?",
                "ما هي أهم العادات والتقاليد في سوق غرداية القديم؟",
                "Expliquez-moi le fonctionnement écologique du ksar de Tafilalet."
            )
        ),
        ConversationTopic(
            id = "topic_souk_shopping",
            title = "التسوق في السوق الشعبي والحديث اليومي",
            titleFr = "Au Marché Traditionnel & Vie Quotidienne",
            description = "ممارسة السؤال عن الأسعار، الزرابي الميزابية، التمور، والمأكولات الشعبية الجزائرية",
            samplePrompts = listOf(
                "كم ثمن هذه الزربية الميزابية التقليدية الجميلة؟",
                "Comment demander le prix et négocier poliment au souk?",
                "ما هي أشهر أطباق غرداية مثل الكسكسي والشخشوخة؟",
                "Quelles sont les variétés de dattes célèbres de la région de Ghardaïa?"
            )
        )
    )
}

data class ConversationTopic(
    val id: String,
    val title: String,
    val titleFr: String,
    val description: String,
    val samplePrompts: List<String>
)
