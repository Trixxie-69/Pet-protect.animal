package Controller;

import Db.Database;
import Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    public List<User> listarAnimais() {
        List<Animal> animais = new ArrayList<>();
//try cach para lidar com possíveis exceções
        // tenta e depois fecha a conexão
        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM Usuario"; 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Animal u = new Animal(
                        rs.getInt("id"),
                        rs.getString("localizacao"),
                        rs.getString("nome"),
                        rs.getString("estado"),
                        rs.getBlob("imagem"),
                        rs.getString("raca"),
                        rs.getString("especie"),
                        
                );
                animais.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animais;
    }
}
