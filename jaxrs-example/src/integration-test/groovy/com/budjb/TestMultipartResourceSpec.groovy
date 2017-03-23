package com.budjb

import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.entity.ContentType
import grails.test.mixin.integration.Integration
import org.grails.plugins.jaxrs.test.JaxrsIntegrationSpec
import org.grails.plugins.jaxrs.test.JaxrsRequestProperties

@Integration
class TestMultipartResourceSpec extends JaxrsIntegrationSpec {
    def 'Ensure can send a multipart file to the resource'() {
        given: "A file to send"
        File file = new File('src/integration-test/groovy/com/budjb/resources/pdf-sample.pdf')
        
        when: "Sending a file to the web service"
        def response = makeRequest(new JaxrsRequestProperties(method: 'POST', 
            uri: '/api/testMultipart/upload',
            headers: ['Content-Type' : ['multipart/form-data; boundary=fnord']],
            body: getMultipartBody(file)))
        def result = new groovy.json.JsonSlurper().parseText(response.bodyAsString)

        then: "The response is correct"
        200 == response.status
        'pdf-sample.pdf' == result.name
        result.size > 0
        'application/pdf' == result.mimeType
    }
        /**
     * Return the list of additional resources to build the JAX-RS servlet with.
     *
     * @return
     */
    @Override
    List getResources() {
        return []
    }
    
    
    private byte[] getMultipartBody(File file) {
        MultipartEntityBuilder e = new MultipartEntityBuilder()
        e.setBoundary("fnord")
        e.addBinaryBody("file", file, ContentType.create("application/pdf"), file.name)
        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        e.build().writeTo(baos)
        return baos.toByteArray()
    }
}
