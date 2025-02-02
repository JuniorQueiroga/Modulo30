package com.seuprojeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/Cliente";
    private static final String user = "postgres";
    private static final String password = "Jr611021";
    private Connection conn;

    public DatabaseConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos para 'clientes'
    public void incluirCliente(String nome, String endereco, String email, String dataNascimento) {
        try {
            String sql = "INSERT INTO clientes (nome, endereco, email, data_nascimento) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.setString(2, endereco);
            pstmt.setString(3, email);
            pstmt.setString(4, dataNascimento);
            pstmt.executeUpdate();
            System.out.println("Cliente incluído com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarClientes() {
        try {
            String sql = "SELECT id, nome, endereco, email, data_nascimento FROM clientes";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Lista de Clientes:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String email = rs.getString("email");
                String dataNascimento = rs.getString("data_nascimento");
                System.out.println("ID: " + id + ", Nome: " + nome + ", Endereço: " + endereco + ", Email: " + email + ", Data de Nascimento: " + dataNascimento);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarCliente(int id, String nome, String endereco, String email, String dataNascimento) {
        try {
            String sql = "UPDATE clientes SET nome = ?, endereco = ?, email = ?, data_nascimento = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.setString(2, endereco);
            pstmt.setString(3, email);
            pstmt.setString(4, dataNascimento);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirCliente(int id) {
        try {
            String sql = "DELETE FROM clientes WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Cliente excluído com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos para 'produtos'
    public void incluirProdutos() {
        try {
            String sql = "INSERT INTO produtos (produto, valor, categoria, descricao) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Incluindo 5 produtos novos
            String[] produtos = {"Produto A", "Produto B", "Produto C", "Produto D", "Produto E"};
            int[] valores = {100, 200, 300, 400, 500};
            String[] categorias = {"Categoria 1", "Categoria 2", "Categoria 3", "Categoria 1", "Categoria 2"};
            String[] descricoes = {"Descrição A", "Descrição B", "Descrição C", "Descrição D", "Descrição E"};

            for (int i = 0; i < produtos.length; i++) {
                pstmt.setString(1, produtos[i]);
                pstmt.setInt(2, valores[i]);
                pstmt.setString(3, categorias[i]);
                pstmt.setString(4, descricoes[i]);
                pstmt.executeUpdate();
            }
            System.out.println("5 Produtos incluídos com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarProdutos() {
        try {
            String sql = "SELECT id, produto, valor, categoria, descricao FROM produtos";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Lista de Produtos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String produto = rs.getString("produto");
                int valor = rs.getInt("valor");
                String categoria = rs.getString("categoria");
                String descricao = rs.getString("descricao");
                System.out.println("ID: " + id + ", Produto: " + produto + ", Valor: " + valor + ", Categoria: " + categoria + ", Descrição: " + descricao);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProduto(int id, String produto, int valor, String categoria, String descricao) {
        try {
            String sql = "UPDATE produtos SET produto = ?, valor = ?, categoria = ?, descricao = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, produto);
            pstmt.setInt(2, valor);
            pstmt.setString(3, categoria);
            pstmt.setString(4, descricao);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirProduto(int id) {
        try {
            String sql = "DELETE FROM produtos WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Produto excluído com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos para 'estoque'
    public void registrarVenda(int clienteId, int produtoId, int quantidade) {
        try {
            String sql = "INSERT INTO estoque (cliente_id, produto_id, quantidade) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, clienteId);
            pstmt.setInt(2, produtoId);
            pstmt.setInt(3, quantidade);
            pstmt.executeUpdate();
            System.out.println("Venda registrada com sucesso!");
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarVendas() {
        try {
            String sql = "SELECT e.id, c.nome AS cliente, p.produto, p.categoria, p.descricao, e.quantidade, e.data_venda " +
                    "FROM estoque e " +
                    "JOIN clientes c ON e.cliente_id = c.id " +
                    "JOIN produtos p ON e.produto_id = p.id";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Lista de Vendas:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String cliente = rs.getString("cliente");
                String produto = rs.getString("produto");
                String categoria = rs.getString("categoria");
                String descricao = rs.getString("descricao");
                int quantidade = rs.getInt("quantidade");
                String dataVenda = rs.getString("data_venda");
                System.out.println("ID: " + id + ", Cliente: " + cliente + ", Produto: " + produto + ", Categoria: " + categoria + ", Descrição: " + descricao + ", Quantidade: " + quantidade + ", Data da Venda: " + dataVenda);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();

        // Incluindo 5 produtos
        db.incluirProdutos();

        // Atualizando o produto com ID 1
        db.atualizarProduto(1, "Produto A Atualizado", 150, "Categoria Atualizada", "Descrição Atualizada");

        // Excluindo o produto com ID 2
        db.excluirProduto(2);

        // Listando todos os produtos
        db.listarProdutos();

        // Exemplos de como usar métodos de 'clientes'
        db.incluirCliente("Cliente X", "Endereço Y", "cliente@exemplo.com", "2000-01-01");
        db.listarClientes();

        // Registrando uma venda
        db.registrarVenda(1, 1, 3);

        // Listando todas as vendas
        db.listarVendas();
    }
}
