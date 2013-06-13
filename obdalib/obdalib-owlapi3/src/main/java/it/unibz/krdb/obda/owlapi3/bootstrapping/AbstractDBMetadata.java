package it.unibz.krdb.obda.owlapi3.bootstrapping;

import it.unibz.krdb.obda.model.OBDADataFactory;
import it.unibz.krdb.obda.model.OBDADataSource;
import it.unibz.krdb.obda.model.OBDAModel;
import it.unibz.krdb.obda.model.impl.OBDADataFactoryImpl;
import it.unibz.krdb.obda.model.impl.RDBMSourceParameterConstants;
import it.unibz.krdb.obda.exception.DuplicateMappingException;
import it.unibz.krdb.obda.io.ModelIOManager;
import it.unibz.krdb.obda.owlapi3.directmapping.DirectMappingEngine;
import it.unibz.krdb.sql.DBMetadata;
import it.unibz.krdb.sql.JDBCConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public abstract class AbstractDBMetadata
{
	
	private OWLOntology onto;
	private OBDAModel model;
	private OBDADataSource source;
	
	protected DBMetadata getMetadata() 
	{
		DBMetadata metadata = null;
		try {
			Class.forName(source.getParameter(RDBMSourceParameterConstants.DATABASE_DRIVER));
		}
		catch (ClassNotFoundException e) { /* NO-OP */ }

		try {
			Connection conn = DriverManager.getConnection(source.getParameter(RDBMSourceParameterConstants.DATABASE_URL),
					source.getParameter(RDBMSourceParameterConstants.DATABASE_USERNAME), source.getParameter(RDBMSourceParameterConstants.DATABASE_PASSWORD));
			metadata = JDBCConnectionManager.getMetaData(conn);
		
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return metadata;
	}
	
	protected void getOntologyAndDirectMappings(OWLOntology onto, OBDAModel model, OBDADataSource source) throws SQLException, DuplicateMappingException, OWLOntologyCreationException, OWLOntologyStorageException {
		this.source = source;	
		DirectMappingEngine engine = new DirectMappingEngine();
		this.model =  engine.extractMappings(model, source);
		this.onto =  engine.getOntology(onto, onto.getOWLOntologyManager(), model);
	}
	
	protected OBDAModel getOBDAModel()
	{
		return this.model;
	}
	
	protected OWLOntology getOWLOntology()
	{
		return this.onto;
	}
	
}