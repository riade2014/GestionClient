package com.mycompagny;

import com.mycompagny.client.Client;
import com.mycompagny.client.ClientRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ClientRepositoryTests {
    @Autowired private ClientRepository repo;
    @Test
    public void testAddNew(){
        Client client = new Client();
        client.setCode("cl01");
        client.setNom("Donfack");
        client.setPrenom("Junior");
        client.setDateEnregistrement(LocalDateTime.now());
        client.setSexe("Masculin");
        client.setTelephone("0650-21-21-00");
        Client saveClient = repo.save(client);
        assertThat(saveClient).isNotNull();
        assertThat(saveClient.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Client> clients = repo.findAll();
        for(Client client:clients){
            System.out.println(client.toString());
        }
        //Assertions.assertThat(clients).hasSizeGreaterThan(0);
    }
    @Test
    public void testUpdate(){
        Integer idCli=1;
        Optional<Client> optionalClient = repo.findById(idCli);
        Client client = optionalClient.get();
        //client.setCode("cl01");
        client.setNom("Donfack");
        repo.save(client);
        /*client.setPrenom("Jean");
        client.setDateEnregistrement(LocalDateTime.now());
        client.setSexe("Masculin");
        client.setTelephone("0650-21-21-00");*/
        Client updateClient = repo.findById(idCli).get();
        assertThat(updateClient.getNom()).isEqualTo("Donfack");
    }
    @Test
    public void testRecherche(){
        Integer idcli=3;
        Optional<Client> optionalClient = repo.findById(idcli);
        assertThat(optionalClient).isPresent();
        System.out.println(optionalClient.get());
    }
    @Test
    public  void testDelete(){
        Integer idcli = 1;
        repo.deleteById(idcli);
        Optional<Client> optionalClient = repo.findById(idcli);
        assertThat(optionalClient).isNotPresent();
    }
}