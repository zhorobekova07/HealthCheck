package tentech.healthcheck.model.enums;

public enum Services {
        ALLERGOLOGY("Аллергология"),
        ANESTHESIOLOGY("Анестезиология"),
        VACCINATION("Вакцинация"),
        GYNECOLOGY("Гинекология"),
        CARDIOLOGY("Кардиология"),
        NEUROSURGERY("Нейрохирургия"),
        ORTHOPEDICS("Ортопедия"),
        OPHTHALMOLOGY("Офтальмология"),
        PSYCHOTHERAPY("Психотерапия"),
        RHEUMATOLOGY("Ревматология"),
        UROLOGY("Урология"),
        ENDOCRINOLOGY("Эндокринология"),
        GASTROENTEROLOGY("Гастроэнтерология"),
        DERMATOLOGY("Дерматология"),
        NEUROLOGY("Неврология"),
        ONCOLOGY("Онкология"),
        OTOLARYNGOLOGY("Отоларингология"),
        PROCTOLOGY("Проктология"),
        PULMONOLOGY("Пульмонология"),
        THERAPY("Терапия"),
        PHLEBOLOGY("Флебология"),
        PHYSIOTHERAPY("Физиотерапия");

        private final String russianName;

        Services(String russianName) {
            this.russianName = russianName;
        }

        public String getRussianName() {
            return russianName;
        }
    }
