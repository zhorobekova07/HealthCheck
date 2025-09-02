//package tentech.healthcheck.service;
//
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.regex.Pattern;
//
//public class SignUp {
//
//    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
//    private static final String DB_USER = "postgres";
//    private static final String DB_PASSWORD = "post";
//
//
//    public class signUp {
//
//        public static void main(String[] args) {
//            // Пример данных от пользователя
//            String firstName = "Назар";
//            String lastName = "Зарлыков";
//            String phoneNumber = "+79773179700";
//
//            if (validate(firstName, lastName, phoneNumber)) {
//                if (saveUser(firstName, lastName, phoneNumber)) {
//                    System.out.println("Пользователь успешно зарегистрирован!");
//                } else {
//                    System.out.println("Ошибка при сохранении пользователя в базу.");
//                }
//            } else {
//                System.out.println("Ошибка валидации данных.");
//            }
//        }
//
//
//        // Проверка данных
//        public static boolean validate(String firstName, String lastName, String phone) {
//            if (firstName == null || firstName.trim().length() < 2) {
//                System.out.println("Имя должно содержать минимум 2 символа.");
//                return false;
//            }
//            if (lastName == null || lastName.trim().length() < 2) {
//                System.out.println("Фамилия должна содержать минимум 2 символа.");
//                return false;
//            }
//            // Проверка телефона: +7 или 8, затем 10 цифр
//            String phoneRegex = "^((\\+7|8)?\\d{10})$";
//            if (phone == null || !Pattern.matches(phoneRegex, phone.replaceAll("\\s", ""))) {
//                System.out.println("Неверный формат номера телефона.");
//                return false;
//            }
//            return true;
//        }
//
//        // Сохранение данных в базу
//        public static boolean saveUser(String firstName, String lastName, String phone) {
//            String sql = "INSERT INTO users (first_name, last_name, phone_number) VALUES (?, ?, ?)";
//            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//                 PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//                stmt.setString(1, firstName.trim());
//                stmt.setString(2, lastName.trim());
//                stmt.setString(3, phone.trim());
//
//                int rows = stmt.executeUpdate();
//                return rows > 0;
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//    }
//}
//
