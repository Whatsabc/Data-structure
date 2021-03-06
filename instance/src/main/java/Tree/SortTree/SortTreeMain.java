package Tree.SortTree;

/**
 * @author Jianshu
 * @time 20200727
 * @since
 */
public class SortTreeMain {
    public static void main(String[] args){

        SortTree sortTree =new SortTree();

        int[] data=new int[]{2,1,9,5,7,14,3,10,8,63};
        for(int i=0;i<data.length;i++){
            sortTree.InsertBST(data[i]);
        }

        System.out.println("\n中序遍历：");
        sortTree.midOrderTraverse(sortTree.root);
        System.out.println("\n广度优先遍历：");
        sortTree.BFS();

        System.out.println("\n最大值为："+sortTree.searchMax());
        System.out.println("\n最小值为："+sortTree.searchMin());

        sortTree.deleteBSTNode(sortTree.root,8);
        //sortTree.deleteNode(sortTree.root,8);
        System.out.println("\n广度优先遍历：");
        sortTree.BFS();
    }
}
