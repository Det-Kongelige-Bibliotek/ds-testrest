package org.openapitools.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-03T13:53:56.637+02:00[Europe/Oslo]")

@Controller
@RequestMapping("${openapi.swaggerCumulusDemo.base-path:/v1}")
public class FindPictureMetadataApiController implements FindPictureMetadataApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public FindPictureMetadataApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
