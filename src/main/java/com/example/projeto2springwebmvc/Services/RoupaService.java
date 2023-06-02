package com.example.projeto2springwebmvc.Services;

import com.example.projeto2springwebmvc.models.Doacao;
import com.example.projeto2springwebmvc.models.Roupa;
import com.example.projeto2springwebmvc.models.Roupa_Doacao;
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
import java.util.Optional;

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

    public Roupa getRoupaPorId(Integer id) {
        Optional<Roupa> result = roupaRepository.findById(id);
        /*if (doacaoEdita.isPresent()){
            return doacaoEdita.get();
        }
        return null;
        Mesma coisa que em baixo*/
        return result.orElse(null);
    }

    /*public Roupa getRoupaPorIdChaveEstrangeira(Integer id) {
        Optional<Roupa> result = Optional.ofNullable(roupaRepository.findByRoupa_doacao(id));
        *//*if (doacaoEdita.isPresent()){
            return doacaoEdita.get();
        }
        return null;
        Mesma coisa que em baixo*//*
        return result.orElse(null);
    }*/

    /*public Roupa getRoupa(Roupa_Doacao roupa_doacao) {
        *//*Optional<Roupa> result = roupaRepository.findById(id);*//*
        *//*if (doacaoEdita.isPresent()){
            return doacaoEdita.get();
        }
        return null;
        Mesma coisa que em baixo*//*
        *//*return result.orElse(null);*//*

        //return roupaRepository.findByRoupa_doacao(roupa_doacao);
    }*/

    public List<Roupa> roupaList(){
        return roupaRepository.findAll();
    }

    public List<Roupa> bsucarTodasRoupas() {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Connection conn = connectionUtil.criarConexao();

        String sql = " SELECT * FROM tb_roupa; ";
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
                listaRoupas.add(roupa);
            }
        } catch (SQLException sqlException) {
            System.out.println("ERRO: " + sqlException.getMessage());
        }
        return listaRoupas;
    }

    public Roupa buscarRoupaPorIdChaveEstrangeira(Integer id) {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Connection conn = connectionUtil.criarConexao();

        String sql = " SELECT * FROM tb_roupa WHERE roupa_doacao_id = ? ; ";
        Roupa roupa = new Roupa();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            roupa.setCategoriaRoupa(CategoriaRoupa.valueOf(rs.getString("categoriaroupa")));
            roupa.setImageSrc(rs.getString("imagesrc"));
            roupa.setStock(rs.getInt("stock"));
            roupa.setTamanhoRoupa(TamanhoRoupa.valueOf(rs.getString("tamanhoroupa")));
            roupa.setTipoRoupa(TipoRoupa.valueOf(rs.getString("tiporoupa")));

            /*while (rs.next()) {
                Roupa roupa = new Roupa();
                roupa.setTipoRoupa(TipoRoupa.valueOf(rs.getString("tiporoupa")));
                roupa.setTamanhoRoupa(TamanhoRoupa.valueOf(rs.getString("tamanhoroupa")));
                roupa.setCategoriaRoupa(CategoriaRoupa.valueOf(rs.getString("categoriaroupa")));
                roupa.setImageSrc(rs.getString("imagesrc"));
                listaRoupas.add(roupa);
            }*/
        } catch (SQLException sqlException) {
            System.out.println("ERRO: " + sqlException.getMessage());
        }
        return roupa;
    }

    public void atualizarRoupa(Integer id, String tipoRoupa, String tamanhoRoupa) {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Connection conn = connectionUtil.criarConexao();

        String sql = " UPDATE tb_roupa " +
                " SET tiporoupa = ? " +
                " , tamanhoroupa = ? " +
                " WHERE roupa_doacao_id = ? ";

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
