二叉堆：一个堆有序的完全二叉树， 叫做二叉堆。

## 堆有序

对于一个二叉树，如果它满足：

1. 任意一个父节点都小于等于它相邻的所有子节点

2. 任意一个父节点都大于等于它相邻的所有子节点

这两种情况都可称之为堆有序（和二叉搜索树不同的是，二叉堆的左儿子和右儿子间没有大小关系）。而堆有序的完全二叉树， 就叫做二叉堆。

### 二叉堆的表示方法

二叉堆可以用一个普通的一维数组来表示，按照从上至下，从左至右的编号，把二叉堆节点从1编号到N，以此作为数组下标，放入一个长度为N+1的数组中。那么就形成了一个用数组表示的二叉堆。例如下面的数组a

要注意的是， a[0]没有存放任何值！ 数组是从a[1]开始存放的。

```
i    0 1 2 3 4 5 6 7 8 9 10 11
a[i] - T S R P N O A E I H  G

                1T
               / \
              /   \
             /     \
            /       \
           2S        R3
          / \        / \
         /   \      /   \
        /     \    /     \
       4P     5N  6O     7A
      / \     / \
     /   \   /   \
     8E  9I 10H  11G
```

### 二叉堆节点间的位置关系
    
见上图图示， 二叉堆的节点位置（对应数组元素下标）间是有数字上的关系的：
    
在一个堆中，位置k的节点的父节点的位置是k/2, 而两个子节点的位置则分别是2k和2k+1。这样的话，在该二叉堆对应的数组中，我们就可以通过计算数组的下标在堆中上下移动： 从a[k]向上一层就令k等于k/2，向下一层则令k等于2k或者2k+1

也就是说，通过这层数字关系， 我们可以找到任意一个堆中节点的父节点和子节点（如果有的话），并进行比较， 在这层基础上，我们就可以来设计我们的堆有序化的算法了。

### 堆有序化的基本算法：上浮和下沉

堆排序的核心，就是堆有序化的算法，而堆有序化的基础，就是针对单个堆节点的有序化

单个堆节点的有序化有两种情况：

1. 当某个节点变得比它的父节点更大而被打破（或是在堆底加入一个新元素时候），我们需要由下至上恢复堆的顺序
2. 当某个节点的优先级下降（例如，将根节点替换为一个较小的元素）时，我们需要由上至下恢复堆的顺序

实现这两种有序化的操作，分别叫做“上浮”（swim）和“下沉” （sink）

基于上面所述，下面我们讨论的原始场景是这样的： 只有一个堆节点是非有序的，而其他堆节点都是有序的。在这种前提下， 该节点对总体堆的顺序破环性只有两种情况：

1. 该节点比它的父节点大
2. 该节点比它的其中一个儿子节点小

（不可能既比父节点大又比儿子节点小，因为我们假设了前提： 其他节点都是堆有序的）

## 上浮实现堆有序

对第一种情况：某个节点比它的父节点大，如下图中位置5上的节点，大于父节点（在图中，节点为从A到Z的字母，顺序上A最小Z最大）

```
                S
               / \
              /   \
             /     \
            /       \
           P         R
          / \        / \
         /   \      /   \
        /     \    /     \
       G     5 T  O       A
      / \     / \
     /   \   /   \
    E     I H     G
```
这个时候我们要做的就是：

1. 交换它和父节点的值， 即P和T互换（这时P将处在5的位置，因为我们假设了前提“其他堆节点都是有序的”，所以互换位置后P肯定是有序的，不用考虑P的顺序问题）
2. 基于1， 我们需要判断交换后T和新的父节点（S）的大小关系，如果还是大于父节点，那么再次交换。不断循环，直到小于父节点或者该节点已经位于根节点为止（最终T到达了根节点的位置）

```
                T
               / \
              /   \
             /     \
            /       \
           S         R
          / \        / \
         /   \      /   \
        /     \    /     \
       G     5 P  O       A
      / \     / \
     /   \   /   \
    E     I H     G
```

下面是我们的上浮方法（和图示的字母不同，我们的节点优先级是用整型数值的大小表示的）
```java
/**
   * @param a 表示堆的数组
   * @param k 堆中的节点位置
   */ 
private void swim (int [] a, int k) {
    while(k>1&&a[k]>a[k/2]){ // 当该结点存在父节点，且大于父节点的时候
        exchange(a, k, k/2); // 交换它和它的父节点值
        k = k/2; // 取得父节点的位置
    }
}
```

## 下沉实现堆有序

对第二种情况：某个节点比它的至少一个儿子节点小，如下图中位置2上的节点（在图中，节点为从A到Z的字母，顺序上A最小Z最大）
```
                T
               / \
              /   \
             /     \
            /       \
          2 H         R
          / \        / \
         /   \      /   \
        /     \    /     \
       G     5 S  O       A
      / \     / \
     /   \   /   \
    E     I N     G
```

这个时候我们要做的就是：

1. 比较该节点（H）的两个子节点的大小， 取得那个比较大的子节点的值（图中S），和该节点交换。 成为父节点的那个较大的子节点， 同时大于原子节点（P）和新的子节点（原父节点H），三个节点间是堆有序的
2. 和1一样，再次判断交换后的该节点和新的子节点（N和G）的关系， 不断循环， 直到大于两个子节点或者到达了底部的叶子节点为止。