# Readme

 JavaGit version 7.0

Author: Xie Yunli, Liu Yu han 

Date: January 10th,  2021

使用说明： 运行gitCommand类中的main函数

详细操作：请见单元测试文档的最后一章GitCommand



## JavaGit项目

组号： 13小组

组长： 谢云丽 2000022758

组员： 刘宇涵 2000022741



## 目录

设计文档

功能亮点

小组分工合作情况及课程项目感受与收获

附录：单元测试

[TOC]



# 一、设计文档

## **数据流：**

用户输入 → GitCommand →
● git log → CommitManager → branchPath.dat → 打印该分支上commit信息
● git commit → CommitManager → newCommit → CommitObject → WriteObject → branchPath.dat
● git branch→ BranchManager → newBranch → allBranch.dat
● git checkout → BranchManager → checkout → ReadBranch → allBranch.dat
● git revert → CommitManager → branchPath.dat → CommitObject



<img src="../Library/Application Support/typora-user-images/image-20210110212327235.png" alt="image-20210110212327235" style="zoom:120%;" /> 

 







## **调用关系：**

![1610221515(1)](file:////Users/apple/Library/Group%20Containers/UBF8T346G9.Office/TemporaryItems/msohtmlclip/clip_image004.jpg)

Git log调用CommitManager类的branchPath属性，读取branchPath对应.dat文件，对该文件调用CommitManager的ReadObject方法，将该分支commit对象打印出来。

![1610221566(1)](file:////Users/apple/Library/Group%20Containers/UBF8T346G9.Office/TemporaryItems/msohtmlclip/clip_image006.jpg)

Git commit调用CommitManager类的newCommit方法，该方法会new一个CommitObject对象从而创建新的提交，再调用CommitManager类的WriteObject方法，将CommitObject对象写入.dat文件中。

在new CommitObject的过程中，会检查commit内容与之前是否一样，若一样则提示“no changes were made”，若不一样则将其写入BranchName.dat文件

![1610284329(1)](file:////Users/apple/Library/Group%20Containers/UBF8T346G9.Office/TemporaryItems/msohtmlclip/clip_image008.jpg)

Git branch调用BranchManager的newBranch方法，该方法会new一个Branch对象，并调用BranchManager的WriteBranch方法，将其写入allBranch.dat文件中，再调用CommitManager类的构造方法，为新的分支创建一个commit管理器。

![1610283360(1)](file:////Users/apple/Library/Group%20Containers/UBF8T346G9.Office/TemporaryItems/msohtmlclip/clip_image010.jpg)

Git checkout调用BranchManager的checkout方法，该方法会调用BranchManager类的ReadBranch方法读取当前所有分支再切换到指定分支，并将head_ofBranch属性置为该分支。

切换分支时，如果分支存在则切换，如果分支不存在则提示“Branch do not exits!”

![1610284126(1)](file:////Users/apple/Library/Group%20Containers/UBF8T346G9.Office/TemporaryItems/msohtmlclip/clip_image012.jpg)

Git revert调用GitCommand类的revert方法，该方法调用CommitManager类的branchPath属性，获得该分支的BranchName.dat文件，对文件调用CommitManager的ReadObject方法，将分支对象读入LinkedList链表中，进行回滚操作，最后将修改后的LinkedList链表写入BranchName.dat文件。

![1610284276(1)](file:////Users/apple/Library/Group%20Containers/UBF8T346G9.Office/TemporaryItems/msohtmlclip/clip_image014.jpg)

## **数据存储结构设计**

我们使用面向对象的数据存储，存储的是对象，例如allBranch.dat存储的是该仓库所有分支对应的BranchObject，BranchName.dat存储的是该分支下所有commit对应的CommitObject。

设计类的时候需要：

1、告诉一个对象自己储存自己

2、每个对象知道如何保存自己——有自己的WriteObject方法

3、检索被存储的对象（通过类方法）——有自己的ReadObject方法

 

插入/修改：找到对应存储文件，插入/修改对象

查询：查询的时候生成这个对象，返回一个对象Object或对象的容器LinkedList

删除：读取返回的容器LinkedList，对其中的Object删除，删除后的容器需要再使用WriteObject方法写回，保证删除。

 

一个GitHub仓库（根目录）有多个分支，一个分支有多个commit：

我们为GitHub仓库建立一个分支管理器，管理该仓库下所有的分支；为分支建立一个提交管理器，管理该分支下所有的commit。BranchManager-BranchObject关系，CommitManager-CommitObject关系，这两个关系都是一对多关系。

一对多关系我们使用多个存储文件，例如BranchManager-BranchObject关系：一个仓库对应多个分支，我们设计一个存储文件是仓库对应allBranch.dat，一个存储文件是分支的BranchName.dat

![img](file:////Users/apple/Library/Group%20Containers/UBF8T346G9.Office/TemporaryItems/msohtmlclip/clip_image016.jpg)

 

allBranch.dat存储的是BranchObject对象，下表为对象中一部分属性的展示：

| 属性名      | 类型         |      | 说明                                 |
| ----------- | ------------ | ---- | ------------------------------------ |
| BranchName1 | BranchObject |      | 根据BranchPath找到对应分支的.dat文件 |
| BranchPath  | String       | 主键 |                                      |

 

BranchName.dat存储的是CommitObject对象，下表为对象中一部分属性的展示：

| 属性名     | 类型         |      | 说明                                 |
| ---------- | ------------ | ---- | ------------------------------------ |
| Commit     | CommitObject |      |                                      |
| CommitPath | String       |      | 根据CommitPath找到该分支下提交的文件 |
| CommitKey  | String       | 主键 |                                      |
| BranchPath | String       | 外键 |                                      |





## **类及其说明**

 

BranchManager类：

属性：

LinkedList<Branch> allBranch 存储该仓库下所有Branch对象

String github 仓库地址

int numOfbranch 该仓库分支数量

Branch head_ofBranch head头

 

方法：

BranchManager(String github) 构造方法

checkout(String branchName) 切换分支

Head() 返回head头分支名

newBranch(String branchName) 新建分支

WriteBranch() 将该分支信息写入allBranch.dat文件

LinkedList<Branch> ReadBranch() 从allBranch.dat文件中读取所有分支信息，并写入LinkedList链表中

 

Branch类：

属性：

String branchName 分支名

String github 该分支所在仓库的地址

String branchPath 该分支.dat文件地址

 

方法：

Branch(String branchName) 构造方法

setGithub(String github) 设置仓库所在地址

toString() 将分支等信息写入String，便于后续存储

 

CommitManager类：

属性：

LinkedList<CommitObject> commitList 存储该分支下所有CommitObject对象

int numberOfCommits 该分支下commit总数

String branchName commit所在分支名

String Github 仓库地址

String branchPath 该commit所在分支地址

int index 编号

String head 该分支的head头

 

方法：

CommitManager() 无参构造方法

CommitManager(String Github,String branchName) 有参构造方法

newCommit(String path, String author, String committer, String message) 新建commit

LinkedList<String> iterator()

LinkedList<CommitObject> ReadObject(String branchpath) 将该分支下所有的CommitObject对象读入LinkedList链表中

isEmpty() 判断分支是否为空（没有commit）

WriteObject() 将commit等信息写入该分支.dat文件中

 

CommitObject类：

属性：

String path 原文件地址

File file 文件

String versionPath KeyValue文件地址

String author 用户名

String committer 提交者IP地址

String message 提交信息

String commitKey 提交的哈希值

String parent 提交的parent

 

方法：

CommitObject() 无参构造

CommitObject(String path, String author, String committer, String message) 有参构造

GetKey() 返回commitKey值

setParent(String parent) 设置该提交的parent

writeVersionFile(String branchPath) 将提交的文件写入该分支文件夹下

equals(CommitObject that) 判断两次提交内容是否相等

compareTo(CommitObject that) 

toString() 将提交等信息写入String中，便于后续存储

 

KeyValue类：

属性：

String path 路径地址

String key 文件的哈希值

File file 文件

String branchPath 分支地址

String versionPath 以KeyValue形式存储文件地址

 

方法：

KeyValue() 无参构造

KeyValue(String path) 有参构造

GetKey() 读取并返回文件哈希值属性

GetversionPath() 读取并返回KeyValue文件地址

GetValue(String key) 根据Key查找Value

toString() 将文件等信息存入String，便于后续存储

String SHA1_Print(File file) 返回文件哈希值

SHA1_Checksum(InputStream inputstream) 计算输入内容哈希值

 

BlobObject类：

属性：

String type 值为blob

String key 哈希值

 

方法：

BlobObject(String path) 构造方法

GetKey() 读取并返回哈希值

 

TreeObject类：

属性：

String type 值为tree

String path 文件夹路径地址

String key 文件夹哈希值

File file 文件

String vrtype 值为100644

String temp

 

方法：

TreeObject (String path) 构造方法

GetKey() 读取并返回哈希值

 

 

# 二、细节亮点

## Readme

 JavaGit version 7.0

Author: Xie Yunli, Liu Yu han 

Date: January 10th,  2021



### 功能亮点

设计思路来自于LinkedList之中拥有一个静态内部类Node。

1. 正如Node之中存储自己的next、item信息，我们设计了内部类Branch、CommitObject。

1. 正如LinkedList在堆空间中存储类的实例化对象的头结点信息，我们在仓库根目录中存储allBranch.dat来找到所有分支的文件存储地址。

1. 正如Node拥有指向下一个结点的item的存储地址一样，我们在每个BranchName.dat文件中存储了指向versionFile.txt的文件存储地址。

#### 1. 面向对象管理数据存储

设计BranchManager、CommitManager对Branch和Commit进行管理：

##### 1)  告诉⼀个对象⾃⼰储存⾃⼰，拥有单独的Branch、CommitObject内部类

##### 2) 每个对象知道如何保存⾃⼰——有⾃⼰的WriteObject⽅法

writeVersionFile 	{CommitObject} 将自己的版本文件写入本地versionFile.txt之中

WriteObject() 	{CommitManager}将自己所管理的分支的提交对象链表写入本地BranchName.dat之中

WriteBranch() 	{BranchManager}将自己所管理的仓库的分支对象链表写入本地的allBranch.dat之中

##### 3）检索被存储的对象（通过类⽅法）——有⾃⼰的ReadObject⽅法

通过implements Serializable接口，重写toString()方法，使得自定义的类可打印，从而完成读取。



### 2. Associative Array

正如在一个Symbol Table financial account 之中，key（account number）- value （transaction details）中，在每个类之中，设计必要的辅助函数，来管理key-value：

1. equals（） 对自定义的类对象进行比较
2. isEmpty（）来判断链表是否为空
3. get()  设计了多种get方法来获取key、value
4. Iterable 来遍历获取keys



### 3、异常处理

#### 错误地址

1. 打开仓库的仓库路径不存在\ 非文件夹： 提示不存在这种仓库
2. commit的文件路径不存在： 提示源文件不存在

#### CommitManager

##### 1. 避免重复提交：

如果曾提交，检验此次提交是否有变化，若无则提示用户：已经是最新的版本。

##### 2. 使用临时对象避免重复写入:

计算哈希的时候创建的是临时提交对象，如果因为重复提交而无需提交，则不将临时的commit对象加入分支链表，且不执行writeVersionFile（）和writeObject()。

#### BranchManager

1. checkout BranchName, 切换的分支不存在： 提示分支不存在
2. branch BranchName, 创建的新分支已经存在：提示分支已存在

#### Git line

1. ##### 避免用户本身地址、信息出现空格的切割错误

   对用户命令行进行切割的时候，考虑到用户的系统文件(夹)名称中会出现空格，用户的message中会有空格断句。是故找到‘-m’的位置，对交互命令进行切割，利用String.join(" ", sub)还原。

2. ##### 命令循环

   考虑了在退出之前，一直处于可输入命令的循环状态之中。

3. ##### 系统命令不与用户正文信息冲突

   考虑到用户的命令之中包含gitCommand的检索之中，只应检索第一次出现此保留关键词，比如在message中出现“commit”不当识别出来，StringBuilder.indexOf(sub,0)中的参数0可以保证。

4. ##### 命令合法性检验

   使用ip地址作为committer，提示用户登陆获得author， 提示用户打开仓库获得默认仓库路径。利用StringBuilder管理gitCommand、endCommand，只有用户正确输入命令系统才会对其进行反映。

   

### 4、 默认设计

1. 打开新的仓库会自动创建master分支
2. 重新打开仓库会默认进入上次head所指向的分支
3. 成功提交commit、branch、checkout等命令，都会有执行成功的用户反馈信息
4. 提示用户正确的指令使用方法





# 三、小组分工情况及收获

|                       |                                                              |
| --------------------- | ------------------------------------------------------------ |
| **谢云丽 2000022758** | commitManager, branch, branchManager, gitCommand命令行设计，修改commit<br />修改代码结构设计，整理代码注释<br />撰写ppt，演讲ppt<br />单元测试<br />文档撰写：单元测出、功能亮点、整合总文档 |
|                       | 课程项目感受与收获：<br />1. 体会并使用了Java语言面向对象的设计与思考<br/>2. 学习到封装的必要性，因为早期版本中类与类之间耦合及聚合没有设计好，封装边界不明晰，造成大量的时间进行逻辑设计<br/>3. 学习到数据流图的必要性，内存代码与文件系统的交互，因为没有提前设计好，造成大量时间修改路径及更改<br/>4. 学习到测试文档及测试设计的必要性，因早期没有测试记录，耗费大量时间进行了测试的重复工作。没有文档记录造成测试思路与结果不清晰<br/>5. 学习到总体设计的必要性，因为没有提前设计好总体设计，造成两次结构大概，代码出现多次debug<br/>6. 通过gitCommand的命令行交互处理，学到了用户视角的思考，用户的命令行中会有多种意外情况<br/>7. 收获代码异常处理的经验，entry、exit、null、duplicate等问题 |
| **刘宇涵 2000022741** | 初版commit，git commit命令行设计<br/>revert功能及相关调试<br/>部分PPT<br/>文档撰写：数据流、数据存储结构设计、类及其说明<br/> <br/> |
|                       | 课程项目感受与收获：<br/>深刻体会Java面向对象思想<br/>了解Git底层逻辑<br/>提升Java编程能力 |



