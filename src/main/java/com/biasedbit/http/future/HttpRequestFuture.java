/*
 * Copyright 2012 Bruno de Carvalho
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

package com.biasedbit.http.future;

import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="http://biasedbit.com/">Bruno de Carvalho</a>
 */
public interface HttpRequestFuture<T> {

    static final Throwable INTERRUPTED        = new Throwable("Interrupted");
    static final Throwable CANCELLED          = new Throwable("Cancelled");
    static final Throwable CANNOT_CONNECT     = new Throwable("Cannot connect");
    static final Throwable CONNECTION_LOST    = new Throwable("Connection lost");
    static final Throwable SHUTTING_DOWN      = new Throwable("Shutting down");
    static final Throwable EXECUTION_REJECTED = new Throwable("Execution rejected by connection");
    static final Throwable TIMED_OUT          = new Throwable("Request execution timed out");
    static final Throwable INVALID_REDIRECT   = new Throwable("Redirect without Location header");

    T getProcessedResult();

    HttpResponse getResponse();

    HttpResponseStatus getStatus();

    int getResponseStatusCode();

    boolean isSuccessfulResponse();

    void markExecutionStart();

    long getExecutionTime();

    long getExistenceTime();

    boolean isDone();

    boolean isSuccess();

    boolean isCancelled();

    Throwable getCause();

    boolean cancel();

    boolean setSuccess(T processedResult, HttpResponse response);

    boolean setFailure(Throwable cause);

    boolean setFailure(HttpResponse response, Throwable cause);

    void addListener(HttpRequestFutureListener<T> listener);

    void removeListener(HttpRequestFutureListener<T> listener);

    void setAttachment(Object attachment);

    Object getAttachment();

    HttpRequestFuture<T> await() throws InterruptedException;

    HttpRequestFuture<T> awaitUninterruptibly();

    boolean await(long timeout, TimeUnit unit) throws InterruptedException;

    boolean await(long timeoutMillis) throws InterruptedException;

    boolean awaitUninterruptibly(long timeout, TimeUnit unit);

    boolean awaitUninterruptibly(long timeoutMillis);
}