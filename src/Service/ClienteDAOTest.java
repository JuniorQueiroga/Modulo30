package Service;

import Cliente.Cliente;
import Dao.ClienteDAO;
import Dao.ClienteDaoMock;
import Dao.IClienteDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteDAOTest {
    private IClienteDAO clienteDAO;

    private Cliente cliente;

    public ClienteDAOTest() {
        clienteDAO = new ClienteDaoMock();
    }

    @Before
    public void init() {
        cliente = new Cliente();
        cliente.setCpf(12345678910L);
        cliente.setNome("Queiroga");
        cliente.setCidade("Barueri");
        cliente.setEnd("Av Tucunare");
        cliente.setEstado("SP");
        cliente.setNumero(1000);
        cliente.setTel(11972378888L);
        clienteDAO.salvar(cliente);
    }

    @Test
    public void pesquisarCliente() {
        Cliente clienteConsultado = clienteDAO.buscarPorCPF(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void excluirCliente() {
        clienteDAO.excluir(cliente.getCpf());
    }

    @Test
    public void alterarCliente() {
        cliente.setNome("Queiroga");
        clienteDAO.alterar(cliente);
        Assert.assertEquals("Queiroga", cliente.getNome());
    }
}
