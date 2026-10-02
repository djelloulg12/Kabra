package com.example.data.repository

import com.example.data.model.AppLanguage

object AppStrings {

    fun appTitle(lang: AppLanguage) = if (lang.isArabic) "دليل غرداية الثقافي" else "Guide Culturel de Ghardaïa"
    fun republicName(lang: AppLanguage) = if (lang.isArabic) "الجمهورية الجزائرية الديمقراطية الشعبية" else "République Algérienne Démocratique et Populaire"
    fun ministryName(lang: AppLanguage) = if (lang.isArabic) "وزارة التكوين والتعليم المهنيين • ولاية غرداية" else "Ministère de la Formation et de l'Enseignement Professionnels • Wilaya de Ghardaïa"
    fun projectTitle(lang: AppLanguage) = if (lang.isArabic) "مشروع البرنامج الثقافي والسياحي والترفيهي" else "Programme Culturel, Touristique et Récréatif"
    fun delegationTitle(lang: AppLanguage) = if (lang.isArabic) "لفائدة وفد رعايا جمهورية النيجر الشقيقة 🇩🇿🤝🇳🇪" else "Au profit de la délégation de la République du Niger 🇩🇿🤝🇳🇪"

    // Tabs
    fun tabSchedule(lang: AppLanguage) = if (lang.isArabic) "البرنامج" else "Programme"
    fun tabMap(lang: AppLanguage) = if (lang.isArabic) "الخريطة" else "Carte"
    fun tabLandmarks(lang: AppLanguage) = if (lang.isArabic) "المعالم" else "Patrimoine"
    fun tabAi(lang: AppLanguage) = if (lang.isArabic) "المحادثة" else "Assistant IA"
    fun tabParticipants(lang: AppLanguage) = if (lang.isArabic) "الوفد (39)" else "Délégation (39)"
    fun tabAbout(lang: AppLanguage) = if (lang.isArabic) "عن الدليل" else "À propos"

    // Stats
    fun statDuration(lang: AppLanguage) = if (lang.isArabic) "المدة" else "Durée"
    fun statDurationVal(lang: AppLanguage) = if (lang.isArabic) "45 يوماً" else "45 Jours"
    fun statDelegation(lang: AppLanguage) = if (lang.isArabic) "الوفد" else "Délégation"
    fun statDelegationVal(lang: AppLanguage) = if (lang.isArabic) "39 متكوناً" else "39 Stagiaires"
    fun statResidency(lang: AppLanguage) = if (lang.isArabic) "المقر" else "Résidence"
    fun statResidencyVal(lang: AppLanguage) = if (lang.isArabic) "واد نشو" else "Oued Nechou"

    // Actions & buttons
    fun viewDetails(lang: AppLanguage) = if (lang.isArabic) "عرض التفاصيل والشرح" else "Voir détails & explications"
    fun exploreMap(lang: AppLanguage) = if (lang.isArabic) "استعراض مسار ومعالم الوفد على الخريطة التفاعلية" else "Explorer le parcours sur la carte interactive"
    fun mapButton(lang: AppLanguage) = if (lang.isArabic) "الخريطة" else "Carte"
    fun directions(lang: AppLanguage) = if (lang.isArabic) "الملاحة" else "Navigation GPS"
    fun programSchedule(lang: AppLanguage) = if (lang.isArabic) "جدول النشاط" else "Fiche activité"
    fun askAiAboutDay(lang: AppLanguage) = if (lang.isArabic) "تحدث مع المرشد الذكي عن هذا النشاط" else "Discuter avec le guide IA de cette activité"
    fun clickParagraphToExplain(lang: AppLanguage) = if (lang.isArabic) "إضغط على الفقرة للشرح" else "Cliquez sur une étape pour l'explication"
    fun detailedParagraphsHeader(lang: AppLanguage) = if (lang.isArabic) "الفقرات التفصيلية لجدول النشاط" else "Déroulement détaillé du programme"
    fun justificationHeader(lang: AppLanguage) = if (lang.isArabic) "سبب ومبررات الاختيار" else "Justification et objectifs de l'activité"
    fun justificationSub(lang: AppLanguage) = if (lang.isArabic) "الشرح البيداغوجي والتاريخي المعتمد في الوثيقة الرسمية" else "Explication pédagogique et historique officielle"
    fun highlightsHeader(lang: AppLanguage) = if (lang.isArabic) "المحطات الثقافية البارزة" else "Points d'intérêt culturels majeurs"
    fun locationLabel(lang: AppLanguage) = if (lang.isArabic) "المكان والموقع:" else "Lieu et destination :"
    fun timingLabel(lang: AppLanguage) = if (lang.isArabic) "التوقيت الزمني المقترح:" else "Horaires proposés :"
    fun distanceLabel(lang: AppLanguage, km: String) = if (lang.isArabic) "المسافة من مقر واد نشو: ≈ $km كم" else "Distance depuis Oued Nechou : ≈ $km km"

    // Search & Filters
    fun searchParticipantsPlaceholder(lang: AppLanguage) = if (lang.isArabic) "ابحث بالاسم، رقم الجواز، أو الهاتف..." else "Rechercher par nom, passeport ou téléphone..."
    fun allFilter(lang: AppLanguage, count: Int) = if (lang.isArabic) "الكل ($count)" else "Tous ($count)"
    fun solarFilter(lang: AppLanguage, count: Int) = if (lang.isArabic) "طاقة شمسية ($count)" else "Énergie Solaire ($count)"
    fun buildingElecFilter(lang: AppLanguage, count: Int) = if (lang.isArabic) "كهرباء معمار ($count)" else "Électr. Bâtiment ($count)"
    fun femaleFilter(lang: AppLanguage, count: Int) = if (lang.isArabic) "الإناث ($count)" else "Femmes ($count)"

    // Chat AI
    fun aiTitle(lang: AppLanguage) = if (lang.isArabic) "شريك المحادثة واللغة التفاعلي" else "Partenaire Linguistique & Guide IA"
    fun aiSub(lang: AppLanguage) = if (lang.isArabic) "ممارسة المحادثة العربية والفرنسية • شروحات البرنامج • مصطلحات الطاقة الشمسية" else "Pratique bilingue Français-Arabe • Explications du programme • Vocabulaire technique"
    fun aiInputPlaceholder(lang: AppLanguage) = if (lang.isArabic) "اكتب رسالتك للممارسة أو السؤال بالعربية أو الفرنسية..." else "Écrivez en français ou en arabe pour pratiquer ou poser une question..."
    fun aiTyping(lang: AppLanguage) = if (lang.isArabic) "المرشد الذكي يقوم بصياغة الرد والملاحظات..." else "Le guide IA formule sa réponse bilingue..."

    // Friday Prayer
    fun fridayPrayerBadge(lang: AppLanguage) = if (lang.isArabic) "موعد أسبوعي ثابت" else "Rendez-vous hebdomadaire"
    fun everyFriday(lang: AppLanguage) = if (lang.isArabic) "كل يوم جمعة" else "Chaque Vendredi"
    fun fridayPrayerTitle(lang: AppLanguage) = if (lang.isArabic) "أداء صلاة الجمعة بمسجد «عقبة بن نافع»" else "Prière du Vendredi à la Mosquée « Oqba Ibn Nafi »"
    fun fridayPrayerSub(lang: AppLanguage) = if (lang.isArabic) "حي بوهراوة العليا • تعزيز الروابط الروحية والتعارف مع المصلين" else "Quartier Bouhraoua • Fraternité spirituelle et échange avec les fidèles"
}
