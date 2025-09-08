package Model;

import Db.Database;
import java.sql.*;

public class Animal{
    private int id;
    private String localizacao;
    private String nome;
    private String estado;
    private Blob imagem;
    private String raca;
    private String especie; 

    public Animal(int id, String localizacao, String nome, String estado, Blob imagem, String raca, String especie) {
        this.id = id;
        this.localizacao = localizacao;
        this.nome = nome;
        this.estado = estado; 
        this.imagem = imagem;
        this.raca = raca;
        this.especie = especie;
    }

    // Getters e setters
    public int getId() 
    { return id; }
    public void setId(int id)
    { this.id = id; }

    public String getLocalizacao()
    { return localizacao; }
    public void setLocalizacao(String localizacao)
    { this.localizacao = localizacao; }

    public String getNome() 
    { return nome; }
    public void setNome(String nome) 
    { this.nome = nome; }

    public String getEstado()
    { return estado; } 
    public void setEstado(String estado)
    { this.estado = estado;}

    public Blob getImagem() 
    { return imagem; }
    public void setImagem(Blob imagem)
    { this.imagem = imagem; }

    public String getRaca()
    { return raca; }
    public void setRaca(String raca)
    { this.raca = raca;}

    public String getEspecie()
    { return especie;}
    public void setEspecie(String especie)
    { this.especie = especie; }

  

    

    // Sobrescrevendo toString para exibir dados do usuário
    @Override
    public String toString() {
        return "ID: " + id + ", Localizacao: " + localizacao + ", Nome: " + nome + ", Estado: " +  estado + ", Imagem: " + imagem + ", Raca: " + raca + ", Especie:" + especie; 
    }
}

 public void adicionarAnimal(Animal animal, String imagemPath) {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO Animal (localizacao, nome, estado, imagem, raca, especie) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, animal.getLocalizacao());
            pstmt.setString(2, animal.getNome());
            pstmt.setString(3, animal.getEstado());

            // Lendo imagem se houver caminho válido
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
