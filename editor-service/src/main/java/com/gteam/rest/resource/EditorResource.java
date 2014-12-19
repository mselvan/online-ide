package com.gteam.rest.resource;

import com.gteam.ide.CompilationResult;
import com.gteam.ide.ExecutionResult;
import com.gteam.ide.LanguageProcessor;
import com.gteam.ide.LanguageProcessorFactory;
import com.gteam.model.SourceCode;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Created by nagarajan on 18/12/14.
 */
public class EditorResource extends ServerResource {

    @Post("application/json")
    public Representation execute(Representation representation) throws Exception {
        String lang = getAttribute("lang");
        JacksonRepresentation<SourceCode> jsonRepresentation = new JacksonRepresentation<SourceCode>(representation, SourceCode.class);
        SourceCode javaSource = jsonRepresentation.getObject();

        LanguageProcessor processor = LanguageProcessorFactory.get(lang);
        final CompilationResult compilationResult = processor.compile(javaSource.getSource());
        final ExecutionResult result = processor.run(compilationResult);
        return new JacksonRepresentation<>(result);
    }

}
