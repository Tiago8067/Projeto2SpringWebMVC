package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Doacao;
import com.example.projeto2springwebmvc.models.LinhaEncomenda;
import com.example.projeto2springwebmvc.models.RoupaDasEncomendas;
import com.example.projeto2springwebmvc.models.Roupa_Doacao;
import com.example.projeto2springwebmvc.repositories.RoupaDasEncomendasRepository;
import com.example.projeto2springwebmvc.util.ConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class RoupaDasEncomendasService {
    @Autowired
    private RoupaDasEncomendasRepository roupaDasEncomendasRepository;

    public void salvarRoupaDasEncomendas(RoupaDasEncomendas roupaDasEncomendas) {
        roupaDasEncomendasRepository.save(roupaDasEncomendas);
    }

    public List<RoupaDasEncomendas> roupaDasEncomendasList(){
        return roupaDasEncomendasRepository.findAll();
    }

    public RoupaDasEncomendas getRoupaDasEncomendasPorId(Integer id) {
        Optional<RoupaDasEncomendas> result = roupaDasEncomendasRepository.findById(id);

        return result.orElse(null);
    }

    public void atualizarRoupaDasEncomendas(Integer id, String tipoRoupa, String tamanhoRoupa) {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Connection conn = connectionUtil.criarConexao();

        String sql = " UPDATE tb_roupa_das_encomendas " +
                " SET tiporoupa = ? " +
                " , tamanhoroupa = ? " +
                " WHERE linha_encomenda_id = ? ";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tipoRoupa);
            preparedStatement.setString(2, tamanhoRoupa);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            System.out.println("ERRO: " + sqlException.getMessage());
        }
    }
}
