package dk.kb.picture.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.kb.picture.model.PictureMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-25T11:58:48.758+02:00")

@Controller
public class FindPictureMetadataApiController implements FindPictureMetadataApi {

    private static final Logger log = LoggerFactory.getLogger(FindPictureMetadataApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public FindPictureMetadataApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<PictureMetadata> getPictureMetadata(@ApiParam(value = "ID of Picture to return",required=true) @PathVariable("pictureId") Long pictureId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PictureMetadata>(objectMapper.readValue("{  \"firstName\" : \"firstName\",  \"lastName\" : \"lastName\",  \"id\" : 0}", PictureMetadata.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PictureMetadata>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PictureMetadata>(HttpStatus.NOT_IMPLEMENTED);
    }

}
