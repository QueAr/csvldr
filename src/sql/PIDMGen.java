/*
 * PIDMGen.java
 *
 * Created on March 29, 2007, 4:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 * CSV Loader
 * Copyright 2007, 2009, 2010 Adrian Wijasa
 *
 * This file is part of CSV Loader.
 *
 * CSV Loader is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CSV Loader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CSV Loader.  If not, see <http://www.gnu.org/licenses/>.
 */

package sql;

import forms.Main;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 * New PIDM Generator
 *
 * This Java Class also makes use of a PL/SQL Package: YUKSHRD within WIJASA
 * schema. For more info about YUKSHRD, please refer to:
 * 1. BANAPP:[BANNER.BANALUM.ALUMNI.HELP]YUKSHRD.LIS in SAINTS VMS
 * 2. ALM$DBPROCS:YUKSHR1.SQL in SAINTS VMS
 *
 * @author awijasa
 */
public class PIDMGen {
    
    /** Creates a new instance of PIDMGen */
    public PIDMGen( Main main ) throws SQLException {
        this.main = main;
        quantity = main.csvPanel.csvTable.getRowCount(); // How many PIDMs need to be generated?
        pidmArrayList = new ArrayList<String>();
        generate();
    }
    
    private void closeConnection() {
        try {
            if( stmt != null )
                stmt.close();

            if( conn != null )
                conn.close();
        }
        catch( SQLException e ) {}
    }
    
    private void generate() throws SQLException {
        try {
            /* Connect to a TNS in Oracle */
            DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection( "jdbc:oracle:thin:@" + main.host + ":" + main.port + ":" + main.sid, main.user, main.password );

            /* Call the PL/SQL Package Procedure for generating PIDMs */
            stmt = conn.prepareCall( "{call wijasa.yukshrd.p_generate_pidm( ? )}" );
            pidm = 1; // Set the floor PIDM

            for( int i = 0; i < quantity; i++ ) {
                stmt.setInt( 1, pidm );
                stmt.registerOutParameter( 1, OracleTypes.NUMBER );
                stmt.executeUpdate();
                
                pidm = stmt.getInt( 1 ); // Get the new PIDM
                pidmArrayList.add( "" + pidm );
                pidm++; // Set the new floor PIDM
            }

            closeConnection();
        }
        catch( SQLException e ) {
            closeConnection();
            throw new SQLException( e.getMessage() );
        }
    }
    
    public ArrayList<String> getPidms() {
        return pidmArrayList;
    }
    
    private CallableStatement stmt;
    private Connection conn;
    private int pidm;
    private int quantity;
    private Main main;
    private ArrayList<String> pidmArrayList;
}
