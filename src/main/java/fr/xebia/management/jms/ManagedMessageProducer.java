/*
 * Copyright 2008-2010 Xebia and the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.xebia.management.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;

import fr.xebia.jms.wrapper.MessageProducerWrapper;
import fr.xebia.management.jms.ManagedConnectionFactory.Statistics;

/**
 * 
 * @author <a href="mailto:cyrille@cyrilleleclerc.com">Cyrille Le Clerc</a>
 */
public class ManagedMessageProducer extends MessageProducerWrapper {

    private final Statistics statistics;

    public ManagedMessageProducer(MessageProducer delegate, Statistics statistics) {
        super(delegate);
        this.statistics = statistics;
    }

    @Override
    public void close() throws JMSException {
        try {
            super.close();
        } finally {
            statistics.incrementCloseMessageProducerCount();
        }
    }

    @Override
    public void send(Destination destination, Message message) throws JMSException {
        long timeBefore = System.currentTimeMillis();
        try {
            super.send(destination, message);
        } catch (JMSException e) {
            statistics.incrementSendMessageExceptionCount();
            throw e;
        } catch (RuntimeException e) {
            statistics.incrementSendMessageExceptionCount();
            throw e;
        } finally {
            statistics.incrementSendMessageCount();
            statistics.incrementSendMessageDurationInMillis(System.currentTimeMillis() - timeBefore);
        }
    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        long timeBefore = System.currentTimeMillis();
        try {
            super.send(destination, message, deliveryMode, priority, timeToLive);
        } catch (JMSException e) {
            statistics.incrementSendMessageExceptionCount();
            throw e;
        } catch (RuntimeException e) {
            statistics.incrementSendMessageExceptionCount();
            throw e;
        } finally {
            statistics.incrementSendMessageCount();
            statistics.incrementSendMessageDurationInMillis(System.currentTimeMillis() - timeBefore);
        }
    }

    @Override
    public void send(Message message) throws JMSException {
        long timeBefore = System.currentTimeMillis();
        try {
            super.send(message);
        } catch (JMSException e) {
            statistics.incrementSendMessageExceptionCount();
            throw e;
        } catch (RuntimeException e) {
            statistics.incrementSendMessageExceptionCount();
            throw e;
        } finally {
            statistics.incrementSendMessageCount();
            statistics.incrementSendMessageDurationInMillis(System.currentTimeMillis() - timeBefore);
        }
    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        long timeBefore = System.currentTimeMillis();
        try {
            super.send(message, deliveryMode, priority, timeToLive);
        } catch (JMSException e) {
            statistics.incrementSendMessageExceptionCount();
            throw e;
        } catch (RuntimeException e) {
            statistics.incrementSendMessageExceptionCount();
            throw e;
        } finally {
            statistics.incrementSendMessageCount();
            statistics.incrementSendMessageDurationInMillis(System.currentTimeMillis() - timeBefore);
        }
    }
}
