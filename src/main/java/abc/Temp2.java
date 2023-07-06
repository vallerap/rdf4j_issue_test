package abc;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import java.net.URISyntaxException;
import java.util.Collection;

public class Temp2 {


    public static void main(String[] args) throws IOException {
        SailRepository sailRepository = new SailRepository(new MemoryStore());
        RepositoryConnection connection = sailRepository.getConnection();

        StringReader stringReader = new StringReader("@prefix owl: <http://www.w3.org/2002/07/owl#> .\n" +
                "@prefix cycAnnot: <http://sw.cyc.com/CycAnnotations_v1#> .\n" +
                "@prefix cyc: <http://sw.cyc.com/concept/> .\n" +
                "@prefix dbpedia: <http://dbpedia.org/resource/> .\n" +
                "@prefix ctag: <http://commontag.org/ns#> .\n" +
                "@prefix opencyc: <http://sw.opencyc.org/concept/> .\n" +
                "@prefix foaf: <http://xmlns.com/foaf/0.1/> .\n" +
                "@prefix csw: <http://semantic-web.at/ontologies/csw.owl#> .\n" +
                "@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .\n" +
                "@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
                "@prefix skos: <http://www.w3.org/2004/02/skos/core#> .\n" +
                "@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .\n" +
                "@prefix dc: <http://purl.org/dc/elements/1.1/> .\n" +
                "@prefix freebase: <http://rdf.freebase.com/ns/> .\n" +
                "@prefix dcterms: <http://purl.org/dc/terms/> .\n" +
                "@prefix tags: <http://www.holygoat.co.uk/owl/redwood/0.1/tags/> .\n" +
                "@prefix sesame: <http://www.openrdf.org/schema/sesame#> .\n" +
                "@prefix fn: <http://www.w3.org/2005/xpath-functions#> .\n" +
                "\n" +
                "<http://localhost/alignedtest/0> skos:hasTopConcept <http://localhost/alignedtest/import_1>;\n" +
                "   a skos:ConceptScheme.\n" +
                "\n" +
                "<http://localhost/alignedtest/1> skos:hasTopConcept <http://localhost/alignedtest/import_1>.\n" +
                "\n" +
                "<http://localhost/alignedtest/import_1> a skos:Concept ;\n" +
                "   skos:prefLabel \"imported concept 1\"@en ;\n" +
                "   skos:altLabel \"imported concept 1\"@en ;\n" +
                "   skos:topConceptOf <http://localhost/alignedtest/0> ;\n" +
                "   skos:narrower   <http://localhost/alignedtest/import_2> ;\n" +
                "   skos:topConceptOf <http://localhost/alignedtest/1> .\n" +
                "\n" +
                " <http://localhost/alignedtest/import_2> skos:prefLabel \"import concept 2\"@en.");


        connection.add(stringReader, RDFFormat.TURTLE);

        String query3 = "PREFIX owl:<http://www.w3.org/2002/07/owl#> \n" +
                "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> \n" +
                "PREFIX freebase:<http://rdf.freebase.com/ns/> \n" +
                "PREFIX skos:<http://www.w3.org/2004/02/skos/core#> \n" +
                "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> \n" +
                "PREFIX dbpedia:<http://dbpedia.org/resource/> \n" +
                "PREFIX users:<http://schema.semantic-web.at/users/> \n" +
                "PREFIX tags:<http://www.holygoat.co.uk/owl/redwood/0.1/tags/> \n" +
                "PREFIX skos-xl:<http://www.w3.org/2008/05/skos-xl#> \n" +
                "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
                "PREFIX dcterms:<http://purl.org/dc/terms/> \n" +
                "PREFIX ctag:<http://commontag.org/ns#> \n" +
                "PREFIX foaf:<http://xmlns.com/foaf/0.1/> \n" +
                "PREFIX dc:<http://purl.org/dc/elements/1.1/> \n" +
                "SELECT (?p as ?resource) (?violates as ?property) (?c as ?value) WHERE {{ SELECT DISTINCT ?c ?violates ?p where {graph <http://test.com/importdata> {?c ?p ?x .FILTER (?p IN ( <http://www.w3.org/2004/02/skos/core#hasTopConcept>,<http://www.w3.org/2004/02/skos/core#narrower>,<http://www.w3.org/2004/02/skos/core#broader>,<http://www.w3.org/2004/02/skos/core#related>,<http://www.w3.org/2004/02/skos/core#member>,<http://www.w3.org/2004/02/skos/core#broaderTransitive>,<http://www.w3.org/2004/02/skos/core#narrowerTransitive>)) OPTIONAL { ?c a ?cType }}{select ?p ?domainType where { graph <tmp:ve/uni-schema> { {?p <http://www.w3.org/2000/01/rdf-schema#domain>/(<http://www.w3.org/2002/07/owl#unionOf>/rdf:rest*/rdf:first)*/^rdfs:subClassOf* ?domainType. FILTER isIri(?domainType). } UNION { ?p <http://www.w3.org/2000/01/rdf-schema#subPropertyOf>+ ?parentProperty.?parentProperty <http://www.w3.org/2000/01/rdf-schema#domain>/(<http://www.w3.org/2002/07/owl#unionOf>/rdf:rest*/rdf:first)*/^rdfs:subClassOf* ?domainType. FILTER isIri(?domainType). }}}} BIND (COALESCE(sameTerm(?cType,?domainType),false) AS ?typeMatch) .BIND (<urn:domainViolationBy> as ?violates) .} GROUP BY ?c ?p ?violates HAVING(MAX(?typeMatch) = false)} UNION { SELECT DISTINCT ?c ?violates ?p  where {graph <http://test.com/importdata> { ?x ?p ?c .FILTER (?p IN (<http://www.w3.org/2004/02/skos/core#hasTopConcept>,<http://www.w3.org/2004/02/skos/core#narrower>,<http://www.w3.org/2004/02/skos/core#broader>,<http://www.w3.org/2004/02/skos/core#related>,<http://www.w3.org/2004/02/skos/core#member>,<http://www.w3.org/2004/02/skos/core#broaderTransitive>,<http://www.w3.org/2004/02/skos/core#narrowerTransitive>)) OPTIONAL {?c a ?cType.}}{select ?p ?rangeType where {graph <tmp:ve/uni-schema> {{?p <http://www.w3.org/2000/01/rdf-schema#range>/(<http://www.w3.org/2002/07/owl#unionOf>/rdf:rest*/rdf:first)*/^rdfs:subClassOf* ?rangeType. FILTER isIri(?rangeType). } UNION {?p <http://www.w3.org/2000/01/rdf-schema#subPropertyOf>+ ?parentProperty.?parentProperty <http://www.w3.org/2000/01/rdf-schema#range>/(<http://www.w3.org/2002/07/owl#unionOf>/rdf:rest*/rdf:first)*/^rdfs:subClassOf* ?rangeType. FILTER isIri(?rangeType). }}}}BIND (COALESCE(sameTerm(?cType,?rangeType),false) AS ?typeMatch) .BIND (<urn:rangeViolationBy> as ?violates) .} GROUP BY ?c ?p ?violates HAVING(MAX(?typeMatch) = false)}}";

       try(RepositoryConnection connection2 = sailRepository.getConnection();
            TupleQueryResult result = connection2.prepareTupleQuery(query3).evaluate();
        ){


            result.next();
        }


    }


    public static void add(Collection<Statement> statements, RepositoryConnection repoConnection, Resource... graphs) {
        if (graphs == null || graphs.length == 0 || graphs[0] == null) {
            graphs = new Resource[]{SimpleValueFactory.getInstance().createIRI("http://test.com/importdata")};
        }

        try (RepositoryConnection connection = repoConnection) {
            connection.begin();
            try {
                connection.add(statements, graphs);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                throw e;
            }
        }
    }

    private static Collection<Statement> getStatements(String resource) throws IOException {
        StatementCollector statementCollector = new StatementCollector();
        RDFParser rdfParser = Rio.createParser(Rio.getParserFormatForFileName(resource).orElse(null));
        rdfParser.setRDFHandler(statementCollector);
        try (InputStream stream = Temp.class.getResourceAsStream(resource)) {
            rdfParser.parse(stream, "");
        }
        return statementCollector.getStatements();
    }
}
