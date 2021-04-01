
## Experiments using [ViolatIntegration](https://plugins.jetbrains.com/plugin/16397-violatintegration) plugin to find Linearizability Violations in ADT implementations in the open source repositories




### **How To Reproduce these results**

1. Clone this repository
2. Open it in Intellij that has ViolatIntegration installed
3. Set up the run-configuration of the data structure you want to test for linearizability violations
4. Run Violat and get the results



### **Results Summary**

___________

| Program           | Number of Violations| 
| ---------------   |-------------------- |
| AccountABA        | 76                  |
| QueueWrong        | 45                  |
| Account           | 0                   |
| LazyList          | 1                   |
| Sequence          | 85                  |
| StampedAccount    | 56                  |
| LinkedList        | 58                  |
| MyHashMap         | 27                  |
| NonBlocking       | 16                  |
| QueueSynchronized | 0                   |
| **Total**         | 365                 |


### Result for AccountABA
![Result for AccountABA](results/AccountABAResult.png)

### Result for QueueWrong
![Result for QueueWrong](results/QueueWrongResult.png)

### Result for Account
![Result for Account](results/AccountResult.png)		

### Result for LazyList
![Result for LazyList](results/LazyListResult.png)	

### Result for Lazy List in Detail
![Result for Lazy List in Detail](results/LazyListResultDetail.png)		

### Result for Sequence
![Result for Sequence](results/SequenceResult.png)

### Result for Stamped Account
![Result for Stamped Account](results/StampedAccountResult.png)

### Result for LinkedList
![Result for LinkedList](results/LinkedListResult.png)		

### Result for MyHashMap
![Result for MyHashMap](results/MyHashMapResult.png)	

### Result for NonBlockingQueue
![Result for NonBlockingQueue](results/NonBlockingQueue.png)	

### Result for QueueSynchronized
![Result for QueueSynchronized](results/QueueSynchronizedResult.png)