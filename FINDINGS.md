# Findings

## Low traffic on a single cluster

Start 500 process instances with 10 threads

Jobs are only executed on 2 of 4 nodes:


----------------------------------------------------------------------------------------------------------------------------------------------
| id_                                  | name_                       | reporter_          | value_ | timestamp_              | milliseconds_ |
| ------------------------------------ | --------------------------- | ------------------ | ------ | ----------------------- | ------------- |
| 3f3709d6-28b0-11ed-9468-0242ac1e0003 | activity-instance-end       | 172.30.0.3$default | 4577   | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d1-28b0-11ed-9468-0242ac1e0003 | activity-instance-start     | 172.30.0.3$default | 4582   | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d5-28b0-11ed-9468-0242ac1e0003 | executed-decision-elements  | 172.30.0.3$default | 0      | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709da-28b0-11ed-9468-0242ac1e0003 | executed-decision-instances | 172.30.0.3$default | 0      | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d2-28b0-11ed-9468-0242ac1e0003 | job-acquired-failure        | 172.30.0.3$default | 89     | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d8-28b0-11ed-9468-0242ac1e0003 | job-acquired-success        | 172.30.0.3$default | 893    | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d9-28b0-11ed-9468-0242ac1e0003 | job-acquisition-attempt     | 172.30.0.3$default | 426    | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d4-28b0-11ed-9468-0242ac1e0003 | job-execution-rejected      | 172.30.0.3$default | 464    | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709db-28b0-11ed-9468-0242ac1e0003 | job-failed                  | 172.30.0.3$default | 0      | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d3-28b0-11ed-9468-0242ac1e0003 | job-locked-exclusive        | 172.30.0.3$default | 898    | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d7-28b0-11ed-9468-0242ac1e0003 | job-successful              | 172.30.0.3$default | 1791   | 2022-08-30 22:08:15.826 | 1661897295826 |
| 3f3709d0-28b0-11ed-9468-0242ac1e0003 | root-process-instance-start | 172.30.0.3$default | 949    | 2022-08-30 22:08:15.826 | 1661897295826 |
| 49a57a2c-28b0-11ed-9715-0242ac1e0004 | activity-instance-end       | 172.30.0.4$default | 423    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a27-28b0-11ed-9715-0242ac1e0004 | activity-instance-start     | 172.30.0.4$default | 418    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a2b-28b0-11ed-9715-0242ac1e0004 | executed-decision-elements  | 172.30.0.4$default | 0      | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a30-28b0-11ed-9715-0242ac1e0004 | executed-decision-instances | 172.30.0.4$default | 0      | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a28-28b0-11ed-9715-0242ac1e0004 | job-acquired-failure        | 172.30.0.4$default | 166    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a2e-28b0-11ed-9715-0242ac1e0004 | job-acquired-success        | 172.30.0.4$default | 107    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a2f-28b0-11ed-9715-0242ac1e0004 | job-acquisition-attempt     | 172.30.0.4$default | 113    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a2a-28b0-11ed-9715-0242ac1e0004 | job-execution-rejected      | 172.30.0.4$default | 15     | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a31-28b0-11ed-9715-0242ac1e0004 | job-failed                  | 172.30.0.4$default | 0      | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a29-28b0-11ed-9715-0242ac1e0004 | job-locked-exclusive        | 172.30.0.4$default | 102    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a2d-28b0-11ed-9715-0242ac1e0004 | job-successful              | 172.30.0.4$default | 209    | 2022-08-30 22:08:33.328 | 1661897313328 |
| 49a57a26-28b0-11ed-9715-0242ac1e0004 | root-process-instance-start | 172.30.0.4$default | 51     | 2022-08-30 22:08:33.328 | 1661897313328 |
| 543248b0-28b0-11ed-b7d1-0242ac1e0005 | activity-instance-end       | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248ab-28b0-11ed-b7d1-0242ac1e0005 | activity-instance-start     | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248af-28b0-11ed-b7d1-0242ac1e0005 | executed-decision-elements  | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248b4-28b0-11ed-b7d1-0242ac1e0005 | executed-decision-instances | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248ac-28b0-11ed-b7d1-0242ac1e0005 | job-acquired-failure        | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248b2-28b0-11ed-b7d1-0242ac1e0005 | job-acquired-success        | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248b3-28b0-11ed-b7d1-0242ac1e0005 | job-acquisition-attempt     | 172.30.0.5$default | 15     | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248ae-28b0-11ed-b7d1-0242ac1e0005 | job-execution-rejected      | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248b5-28b0-11ed-b7d1-0242ac1e0005 | job-failed                  | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248ad-28b0-11ed-b7d1-0242ac1e0005 | job-locked-exclusive        | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248b1-28b0-11ed-b7d1-0242ac1e0005 | job-successful              | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 543248aa-28b0-11ed-b7d1-0242ac1e0005 | root-process-instance-start | 172.30.0.5$default | 0      | 2022-08-30 22:08:51.027 | 1661897331027 |
| 56cc54b5-28b0-11ed-b57a-0242ac1e0006 | activity-instance-end       | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b0-28b0-11ed-b57a-0242ac1e0006 | activity-instance-start     | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b4-28b0-11ed-b57a-0242ac1e0006 | executed-decision-elements  | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b9-28b0-11ed-b57a-0242ac1e0006 | executed-decision-instances | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b1-28b0-11ed-b57a-0242ac1e0006 | job-acquired-failure        | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b7-28b0-11ed-b57a-0242ac1e0006 | job-acquired-success        | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b8-28b0-11ed-b57a-0242ac1e0006 | job-acquisition-attempt     | 172.30.0.6$default | 15     | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b3-28b0-11ed-b57a-0242ac1e0006 | job-execution-rejected      | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54ba-28b0-11ed-b57a-0242ac1e0006 | job-failed                  | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b2-28b0-11ed-b57a-0242ac1e0006 | job-locked-exclusive        | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc54b6-28b0-11ed-b57a-0242ac1e0006 | job-successful              | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
| 56cc2d9f-28b0-11ed-b57a-0242ac1e0006 | root-process-instance-start | 172.30.0.6$default | 0      | 2022-08-30 22:08:55.393 | 1661897335393 |
----------------------------------------------------------------------------------------------------------------------------------------------



## 5000 process instances started in 3 minutes

all 4 nodes were used, where the most traffic is on the node where all process instances got started

----------------------------------------------------------------------------------------------------------------------------------------------
| id_                                  | name_                       | reporter_          | value_ | timestamp_              | milliseconds_ |
| ------------------------------------ | --------------------------- | ------------------ | ------ | ----------------------- | ------------- |
| a0fb4ad2-28b8-11ed-9468-0242ac1e0003 | activity-instance-end       | 172.30.0.3$default | 25627  | 2022-08-30 23:08:15.827 | 1661900895827 |
| a0fb4acd-28b8-11ed-9468-0242ac1e0003 | activity-instance-start     | 172.30.0.3$default | 25882  | 2022-08-30 23:08:15.827 | 1661900895827 |
| a0fb4ad1-28b8-11ed-9468-0242ac1e0003 | executed-decision-elements  | 172.30.0.3$default | 0      | 2022-08-30 23:08:15.827 | 1661900895827 |
| a0fb4ad6-28b8-11ed-9468-0242ac1e0003 | executed-decision-instances | 172.30.0.3$default | 0      | 2022-08-30 23:08:15.827 | 1661900895827 |
| a0fb4ace-28b8-11ed-9468-0242ac1e0003 | job-acquired-failure        | 172.30.0.3$default | 3060   | 2022-08-30 23:08:15.827 | 1661900895827 |
| a0fb4ad4-28b8-11ed-9468-0242ac1e0003 | job-acquired-success        | 172.30.0.3$default | 3843   | 2022-08-30 23:08:15.827 | 1661900895827 |
| a0fb4ad5-28b8-11ed-9468-0242ac1e0003 | job-acquisition-attempt     | 172.30.0.3$default | 2527   | 2022-08-30 23:08:15.827 | 1661900895827 |
| a0fb4ad0-28b8-11ed-9468-0242ac1e0003 | job-execution-rejected      | 172.30.0.3$default | 1197   | 2022-08-30 23:08:15.827 | 1661900895827 |
| a0fb4ad7-28b8-11ed-9468-0242ac1e0003 | job-failed                  | 172.30.0.3$default | 0      | 2022-08-30 23:08:15.827 | 1661900895827 |
| a0fb4acf-28b8-11ed-9468-0242ac1e0003 | job-locked-exclusive        | 172.30.0.3$default | 4098   | 2022-08-30 23:08:15.827 | 1661900895827 |
| a0fb4ad3-28b8-11ed-9468-0242ac1e0003 | job-successful              | 172.30.0.3$default | 7941   | 2022-08-30 23:08:15.827 | 1661900895827 |
| a0fb4acc-28b8-11ed-9468-0242ac1e0003 | root-process-instance-start | 172.30.0.3$default | 7049   | 2022-08-30 23:08:15.827 | 1661900895827 |
| ab69bbea-28b8-11ed-9715-0242ac1e0004 | activity-instance-end       | 172.30.0.4$default | 8029   | 2022-08-30 23:08:33.327 | 1661900913327 |
| ab69bbe5-28b8-11ed-9715-0242ac1e0004 | activity-instance-start     | 172.30.0.4$default | 7870   | 2022-08-30 23:08:33.327 | 1661900913327 |
| ab69bbe9-28b8-11ed-9715-0242ac1e0004 | executed-decision-elements  | 172.30.0.4$default | 0      | 2022-08-30 23:08:33.327 | 1661900913327 |
| ab69bbee-28b8-11ed-9715-0242ac1e0004 | executed-decision-instances | 172.30.0.4$default | 0      | 2022-08-30 23:08:33.327 | 1661900913327 |
| ab69bbe6-28b8-11ed-9715-0242ac1e0004 | job-acquired-failure        | 172.30.0.4$default | 3252   | 2022-08-30 23:08:33.327 | 1661900913327 |
| ab69bbec-28b8-11ed-9715-0242ac1e0004 | job-acquired-success        | 172.30.0.4$default | 2047   | 2022-08-30 23:08:33.327 | 1661900913327 |
| ab69bbed-28b8-11ed-9715-0242ac1e0004 | job-acquisition-attempt     | 172.30.0.4$default | 1795   | 2022-08-30 23:08:33.327 | 1661900913327 |
| ab69bbe8-28b8-11ed-9715-0242ac1e0004 | job-execution-rejected      | 172.30.0.4$default | 73     | 2022-08-30 23:08:33.327 | 1661900913327 |
| ab69bbef-28b8-11ed-9715-0242ac1e0004 | job-failed                  | 172.30.0.4$default | 0      | 2022-08-30 23:08:33.327 | 1661900913327 |
| ab69bbe7-28b8-11ed-9715-0242ac1e0004 | job-locked-exclusive        | 172.30.0.4$default | 1888   | 2022-08-30 23:08:33.327 | 1661900913327 |
| ab69bbeb-28b8-11ed-9715-0242ac1e0004 | job-successful              | 172.30.0.4$default | 3935   | 2022-08-30 23:08:33.327 | 1661900913327 |
| ab69bbe4-28b8-11ed-9715-0242ac1e0004 | root-process-instance-start | 172.30.0.4$default | 944    | 2022-08-30 23:08:33.327 | 1661900913327 |
| b5f689f8-28b8-11ed-b7d1-0242ac1e0005 | activity-instance-end       | 172.30.0.5$default | 7917   | 2022-08-30 23:08:51.028 | 1661900931028 |
| b5f689f3-28b8-11ed-b7d1-0242ac1e0005 | activity-instance-start     | 172.30.0.5$default | 7894   | 2022-08-30 23:08:51.028 | 1661900931028 |
| b5f689f7-28b8-11ed-b7d1-0242ac1e0005 | executed-decision-elements  | 172.30.0.5$default | 0      | 2022-08-30 23:08:51.028 | 1661900931028 |
| b5f6b10c-28b8-11ed-b7d1-0242ac1e0005 | executed-decision-instances | 172.30.0.5$default | 0      | 2022-08-30 23:08:51.028 | 1661900931028 |
| b5f689f4-28b8-11ed-b7d1-0242ac1e0005 | job-acquired-failure        | 172.30.0.5$default | 3255   | 2022-08-30 23:08:51.028 | 1661900931028 |
| b5f689fa-28b8-11ed-b7d1-0242ac1e0005 | job-acquired-success        | 172.30.0.5$default | 1985   | 2022-08-30 23:08:51.028 | 1661900931028 |
| b5f689fb-28b8-11ed-b7d1-0242ac1e0005 | job-acquisition-attempt     | 172.30.0.5$default | 1792   | 2022-08-30 23:08:51.028 | 1661900931028 |
| b5f689f6-28b8-11ed-b7d1-0242ac1e0005 | job-execution-rejected      | 172.30.0.5$default | 132    | 2022-08-30 23:08:51.028 | 1661900931028 |
| b5f6b10d-28b8-11ed-b7d1-0242ac1e0005 | job-failed                  | 172.30.0.5$default | 0      | 2022-08-30 23:08:51.028 | 1661900931028 |
| b5f689f5-28b8-11ed-b7d1-0242ac1e0005 | job-locked-exclusive        | 172.30.0.5$default | 1962   | 2022-08-30 23:08:51.028 | 1661900931028 |
| b5f689f9-28b8-11ed-b7d1-0242ac1e0005 | job-successful              | 172.30.0.5$default | 3947   | 2022-08-30 23:08:51.028 | 1661900931028 |
| b5f689f2-28b8-11ed-b7d1-0242ac1e0005 | root-process-instance-start | 172.30.0.5$default | 981    | 2022-08-30 23:08:51.028 | 1661900931028 |
| b8910abb-28b8-11ed-b57a-0242ac1e0006 | activity-instance-end       | 172.30.0.6$default | 8427   | 2022-08-30 23:08:55.394 | 1661900935394 |
| b8910ab6-28b8-11ed-b57a-0242ac1e0006 | activity-instance-start     | 172.30.0.6$default | 8354   | 2022-08-30 23:08:55.394 | 1661900935394 |
| b8910aba-28b8-11ed-b57a-0242ac1e0006 | executed-decision-elements  | 172.30.0.6$default | 0      | 2022-08-30 23:08:55.394 | 1661900935394 |
| b8910abf-28b8-11ed-b57a-0242ac1e0006 | executed-decision-instances | 172.30.0.6$default | 0      | 2022-08-30 23:08:55.394 | 1661900935394 |
| b8910ab7-28b8-11ed-b57a-0242ac1e0006 | job-acquired-failure        | 172.30.0.6$default | 3167   | 2022-08-30 23:08:55.394 | 1661900935394 |
| b8910abd-28b8-11ed-b57a-0242ac1e0006 | job-acquired-success        | 172.30.0.6$default | 2125   | 2022-08-30 23:08:55.394 | 1661900935394 |
| b8910abe-28b8-11ed-b57a-0242ac1e0006 | job-acquisition-attempt     | 172.30.0.6$default | 1791   | 2022-08-30 23:08:55.394 | 1661900935394 |
| b8910ab9-28b8-11ed-b57a-0242ac1e0006 | job-execution-rejected      | 172.30.0.6$default | 33     | 2022-08-30 23:08:55.394 | 1661900935394 |
| b8910ac0-28b8-11ed-b57a-0242ac1e0006 | job-failed                  | 172.30.0.6$default | 0      | 2022-08-30 23:08:55.394 | 1661900935394 |
| b8910ab8-28b8-11ed-b57a-0242ac1e0006 | job-locked-exclusive        | 172.30.0.6$default | 2052   | 2022-08-30 23:08:55.394 | 1661900935394 |
| b8910abc-28b8-11ed-b57a-0242ac1e0006 | job-successful              | 172.30.0.6$default | 4177   | 2022-08-30 23:08:55.394 | 1661900935394 |
| b8910ab5-28b8-11ed-b57a-0242ac1e0006 | root-process-instance-start | 172.30.0.6$default | 1026   | 2022-08-30 23:08:55.394 | 1661900935394 |
----------------------------------------------------------------------------------------------------------------------------------------------
