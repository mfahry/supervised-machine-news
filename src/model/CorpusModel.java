/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import config.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfahry
 */
public class CorpusModel {
    Database db;
    ResultSet resultSet;
    PreparedStatement statement;
    
    public CorpusModel() {
        db = new Database("root", "");
    }
    
    public int insertCorpus(String corpusValue, String corpusLabel){
        try {
            statement = db.getCon().prepareStatement(
                "INSERT INTO corpus(corpus_value, corpus_label) values (? , ?)"
            );
            
            statement.setString(1, corpusValue);
            statement.setString(2, corpusLabel);
            
            return statement.executeUpdate(); // jika insert data berhasil 
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(CorpusModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 2; // jika tidak terjadi apa - apa
    }
    
    
    public int updateCorpusforPreProcess(String param){
        try {
            statement = db.getCon().prepareStatement(
                "UPDATE corpus set corpus_value = replace(corpus_value, ? ,' ')"
            );
            
            statement.setString(1, param);
            return statement.executeUpdate(); // jika update data berhasil 
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(CorpusModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 2;
    }
    
    public int updateCorpusLowerCase(){
        try {
            statement = db.getCon().prepareStatement(
                "UPDATE corpus set corpus_value = LOWER(corpus_value)"
            );
            
            return statement.executeUpdate(); // jika update data berhasil 
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(CorpusModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 2;
    }
    
    public int updateCorpusforStemming(String param, String stem){
        try {
            statement = db.getCon().prepareStatement(
                "UPDATE corpus set corpus_value = replace(corpus_value, ? , ?)"
            );
            
            statement.setString(1, param);
            statement.setString(2, stem);
            
            return statement.executeUpdate(); // jika update data berhasil 
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(CorpusModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 2;
    }
    
    public ResultSet selectCorpus(){
        try {
            statement = db.getCon().prepareStatement("SELECT * FROM corpus");
            resultSet = statement.executeQuery();
            return resultSet;
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(StopWordsIndonesianModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
