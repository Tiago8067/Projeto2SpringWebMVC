package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Roupa;
import com.example.projeto2springwebmvc.models.enums.CategoriaRoupa;
import com.example.projeto2springwebmvc.models.enums.TamanhoRoupa;
import com.example.projeto2springwebmvc.models.enums.TipoRoupa;
import com.example.projeto2springwebmvc.repositories.RoupaRepository;
import com.example.projeto2springwebmvc.util.ConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoupaService {
    @Autowired
    private RoupaRepository roupaRepository;

    public void salvarRoupa(Roupa roupa) {
        roupaRepository.save(roupa);
    }

    public List<Roupa> buscarTipoTamanhoUnico() {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Connection conn = connectionUtil.criarConexao();

        //TODO -> Verificar a situacao do sotck

        /*String sql = " SELECT DISTINCT categoriaroupa, imagesrc, tiporoupa, tamanhoroupa, stock FROM tb_roupa; ";*/
        String sql = " SELECT DISTINCT categoriaroupa, imagesrc, tiporoupa, tamanhoroupa FROM tb_roupa; ";
        List<Roupa> listaRoupas = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Roupa roupa = new Roupa();
                roupa.setTipoRoupa(TipoRoupa.valueOf(rs.getString("tiporoupa")));
                roupa.setTamanhoRoupa(TamanhoRoupa.valueOf(rs.getString("tamanhoroupa")));
                roupa.setCategoriaRoupa(CategoriaRoupa.valueOf(rs.getString("categoriaroupa")));
                roupa.setImageSrc(rs.getString("imagesrc"));
                /*roupa.setStock(rs.getInt("stock"));*/
                listaRoupas.add(roupa);
            }
        } catch (SQLException sqlException) {
            System.out.println("ERRO: " + sqlException.getMessage());
        }
        return listaRoupas;
    }
}
