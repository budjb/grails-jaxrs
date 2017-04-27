/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.plugins.jaxrs.servlet

import groovy.transform.CompileStatic
import javax.servlet.ServletInputStream
import javax.servlet.ReadListener


/** 
 *
 * @author Alex Stoia
 */
@CompileStatic    
class ReusableServletInputStream extends ServletInputStream {
    private BufferedInputStream wrappedStream
    private ServletInputStream originalStream
        
    ReusableServletInputStream(ServletInputStream originalStream) {
        super()
        this.originalStream = originalStream
        this.wrappedStream = new BufferedInputStream(originalStream)
    }
        
        
    @Override
    boolean isReady() {
        return originalStream.isReady()
    }
    @Override
    boolean isFinished() {
        return originalStream.isFinished()
    }
    @Override
    void setReadListener(ReadListener readListener) {
        originalStream.setReadListener(readListener)
    }
                
    @Override
    public int read() throws IOException {
        return wrappedStream.read()
    }
    @Override
    boolean markSupported() {
        return wrappedStream.markSupported()
    }
    @Override
    void mark(int readlimit) {
        wrappedStream.mark(readlimit)
    }
    @Override
    void reset() {
        wrappedStream.reset()
    }
}
