package Controller;

import Db.Database;
import Model.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalController {

    public List<Animal> listarAnimais() {
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
                        rs.getString("especie")
                );
                animais.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animais;
    }

  public void adicionarAnimal(Animal animal, String imagemPath) {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO Animal (localizacao, nome, estado, imagem, raca, especie) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, animal.getLocalizacao());
            pstmt.setString(2, animal.getNome());
            pstmt.setString(3, animal.getEstado());
           
            if (imagemPath != null && !imagemPath.isEmpty()) {
                FileInputStream fis = new FileInputStream(imagemPath);
                pstmt.setBinaryStream(4, fis);
            } else {
                pstmt.setNull(4, Types.BLOB);
            }

            pstmt.setString(5, animal.getRaca());
            pstmt.setString(6, animal.getEspecie());

            pstmt.executeUpdate();
            System.out.println("Animal adicionado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarAnimal(Animal animal, String imagemPath) {
        try (Connection conn = Database.getConnection()) {
            String sql = "UPDATE Animal SET localizacao=?, nome=?, estado=?, imagem=?, raca=?, especie=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, animal.getLocalizacao());
            pstmt.setString(2, animal.getNome());
            pstmt.setString(3, animal.getEstado());

            if (imagemPath != null && !imagemPath.isEmpty()) {
                FileInputStream fis = new FileInputStream(imagemPath);
                pstmt.setBinaryStream(4, fis);
            } else {
                pstmt.setNull(4, Types.BLOB);
            }

            pstmt.setString(5, animal.getRaca());
            pstmt.setString(6, animal.getEspecie());
            pstmt.setInt(7, animal.getId());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Animal atualizado com sucesso!");
            } else {
                System.out.println("Animal não encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void removerAnimal(int id) {
            try (Connection conn = Database.getConnection()) {
                String sql = "DELETE FROM Animal WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                int rows = pstmt.executeUpdate();

                if (rows > 0) {
                    System.out.println("Animal removido com sucesso!");
                } else {
                    System.out.println("Animal não encontrado.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

