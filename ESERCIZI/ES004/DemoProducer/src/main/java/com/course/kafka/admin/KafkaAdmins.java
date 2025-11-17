package com.course.kafka.admin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import javax.swing.text.html.Option;
import java.util.*;

public class KafkaAdmins {

    public static void createTopic(
            final String topicName,
            final int partitions,
            final short replications,
            final Properties props
    ) {

        final NewTopic topic = new NewTopic(topicName, partitions, replications);

        try (final AdminClient adminClient = AdminClient.create(props)) {

            ListTopicsResult listTopicsResult = adminClient.listTopics();

            if (!listTopicsResult.names().get().contains(topicName)) {
                final List<NewTopic> topics = Collections.singletonList(topic);
                CreateTopicsResult ret = adminClient.createTopics(topics);
            }

        } catch (Exception e) {
            // TODO: Gestire eventuali problemao
        }

    }

}
