/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.services.client;



import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();

        
        //Crear el mapper y usarlo: 
        //ClienteMapper cm=sqlss.getMapper(ClienteMapper.class)
        //cm...
        ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        ItemMapper im=sqlss.getMapper(ItemMapper.class);
        System.out.println(cm.consultarClientes());
        System.out.println(cm.getCliente(1026585667));
        //cm.agregarItemRentadoACliente(1026585665,6,java.sql.Date.valueOf(LocalDate.now()),java.sql.Date.valueOf(LocalDate.now()));
        // ya agregado un item no se puede volver agregar porque esuna clave unica.
        System.out.println(im.consultarItem(0));
        System.out.println(cm.getCliente(1026585664));
        sqlss.commit();
        sqlss.close();

        
        
    }


}
