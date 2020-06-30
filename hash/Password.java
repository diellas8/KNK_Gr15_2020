package hash;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.Base64;

public class Password {

    private static Connection dbCon ;

    private static Statement statement ;


    public static byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword (byte[] salt,String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Ndodhi nje gabim");
            System.exit(-1);
            return "gabim;";
        }

    }


    public static int getData( String email, String password) throws SQLException {
        String query = "SELECT * from Users where email = '" + email+ "'";
        try {
            initializeDB();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                String dbSalt = resultSet.getString("Salt");
                String dbPassword = resultSet.getString("Hash");
                if (correctPassword(dbSalt, dbPassword, password)) {

                    return Integer.parseInt(resultSet.getString("Statusi"));   // ok
                } else{

                    return 0; // password gabim
            }}
            else {

                return -1;   // email gabim
            }

        } catch (Exception e) {
            return  -2; // gabim db
        }
        finally {
            if(statement!= null)
            statement.close();
            if(dbCon != null)
            dbCon.close();
        }
    }

    public static boolean correctPassword(String salt, String dbPassword, String password) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        byte[] byteSalt = Base64.getDecoder().decode(salt);
        String hashedPassword = hashPassword(byteSalt, password);
        return hashedPassword.equals(dbPassword) ? true : false;
        }

    private static void initializeDB() {
        try {
            if (dbCon == null || dbCon.isClosed())
                dbCon = DriverManager.getConnection("jdbc:sqlite:C:\\Sqlite\\db\\menaxhimi_konsultimeve.db");
            if (statement == null || statement.isClosed())
                statement = dbCon.createStatement();
        } catch (Exception e) {

        }
    }
    }

