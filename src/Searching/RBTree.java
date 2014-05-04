package Searching;

// http://blog.csdn.net/hawksoft/article/details/7014796
// http://jiangzhengjun.iteye.com/blog/562408
public class RBTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


/*using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AlbertAlgorithms
{
    /// <summary>
    /// 节点颜色枚举
    /// </summary>
    public enum RBTreeNodeColor
    {
        Red,
        Black
    }
    /// <summary>
    /// 红黑树节点
    /// </summary>
    public class RBTreeNode
    {
        public int Key { get; set; }
        public RBTreeNode Left { get; set; }
        public RBTreeNode Right { get; set; }
        public RBTreeNode Parent { get; set; }
        public RBTreeNodeColor NodeColor { get; set; }
    }
    /// <summary>
    /// 红黑树
    /// </summary>
    public class RBTree
    {
        /// <summary>
        /// 空节点，充当哨兵
        /// </summary>
        public static readonly RBTreeNode NullNode=new RBTreeNode();
        /// <summary>
        /// 静态构造函数，哨兵为黑色.
        /// </summary>
        static RBTree()
        {
            NullNode.NodeColor = RBTreeNodeColor.Black;
        }
        /// <summary>
        /// 根节点，默认为NullNode.
        /// </summary>
        public RBTreeNode Root = NullNode;
        /// <summary>
        /// 插入节点,这里假设了所有的节点key不相同.
        /// </summary>
        /// <param name="Node"></param>
        public void InsertNode(RBTreeNode Node)
        {
            ///寻找插入节点的插入点（父节点）theY.TheX是循环中间变量.
            RBTreeNode theY = NullNode;
            RBTreeNode theX = Root;
            //注意，如果不为空，则找到的插入节点一定是最底层内节点，仅有空字节点(哨兵节点).
            //这也是红黑树不是高度平衡树的一个重要原因.但这样简化了插入，不用判断补缺.
            while (theX != NullNode)
            {
                theY = theX;
                if (Node.Key > theX.Key)
                {
                    theX = theX.Right;
                }
                else
                {
                    theX = theX.Left;
                }
            }
            //将插入节点的父节点指向theY.
            Node.Parent = theY;
            //如果theY为空，则原来是空树，则新插入的节点是根节点.
            if (theY == NullNode)
            {
                Root = Node;
            }
            else
            {
                //如果有根节点，与theY的Key比较，大于则作为右子节点，小于则
                //作为左节点.
                if (Node.Key > theY.Key)
                {
                    Node.Parent = theY.Right;
                }
                else
                {
                    Node.Parent = theY.Left;
                }
            }
            //开始插入的节点都在非空叶子节点上，将空节点作为其两个子节点.
            Node.Right = NullNode;
            Node.Left = NullNode;
            //颜色置为红，然后整理.
            Node.NodeColor = RBTreeNodeColor.Red;
            RBInsertFixup(Node);
        }
        /// <summary>
        /// 红黑树的插入整理，以保持红黑树性质：非红即白；根和叶子黑；相邻节点不能同为红；
        /// 所有从根到叶子路径上的黑节点(黑度)相同.这里的相邻只会是父子节点间，书上的定义是
        /// 红节点的两个孩子节点都是黑的，表达是等价的.从根到叶子节点看做是树的路径，则路径
        /// 上红节点不能相邻.
        /// </summary>
        /// <param name="Node"></param>
        private void RBInsertFixup(RBTreeNode Node)
        {
            //因为当前节点为红色，从下向根方向整理，如果当前节点的父节点为红色，则违反了红黑树的性质，
            //就需要不断整理，直到当前整理节点的父节点是黑色为止.整理一定会有结束条件，
            //因为根节点肯定为黑。
            while (Node.Parent.NodeColor == RBTreeNodeColor.Red)
            {
                //如果父节点为红色，则爷节点必为黑色（红黑树性质）。
                //如果当前节点的父节点是左孩子
                if (Node.Parent == Node.Parent.Parent.Left)
                {
                    //找到其叔父节点.
                    RBTreeNode theY = Node.Parent.Parent.Right;
                    //如果叔父节点是红色节点，而爷节点为黑色节点。形式就是爷节点为黑，父辈节点为红，由于当前节点为红，
                    //父节点必须改为黑色。而且这种改变不能影响黑度(所有从根到叶子节点的路径中黑节点数相等），那么改变的策略
                    //就是将爷节点变为红色，父辈节点变为黑色，这种改变显然没有改变路径上的黑度。但因为爷节点变为了红色，可能
                    //会导致红黑树性质的改变,就需要继续整理，因此需将整理节点上移到爷节点，继续整理。
                    if (theY.NodeColor == RBTreeNodeColor.Red)
                    {
                        Node.Parent.NodeColor = RBTreeNodeColor.Black;
                        theY.NodeColor = RBTreeNodeColor.Black;
                        Node.Parent.Parent.NodeColor = RBTreeNodeColor.Red;
                        Node = Node.Parent.Parent;
                    }
                    //如果叔父节点是黑色，爷节点也为黑色，父节点因为要改成黑节点，就增加了当前节点路径上的黑度。因此处理就稍微复杂些。
                    else
                    {
                        //如果当前节点是右孩子，先左旋。因为轴是当前节点和父节点，都为红色，因此左旋不会改变黑度，这很关键。
                        if (Node == Node.Parent.Right)
                        {
                            Node = Node.Parent;
                            LeftRotate(Node);
                        }
                        //将父节点改为黑节点，爷节点改为红色节点，当前节点路径上的黑度没变，但叔父路径上的黑度被减了1，为了保持黑度不变，因此需要右旋
                        //其实就是将父节点上升到爷节点，原来的爷节点降为叔父节点，这样黑度就平衡了。
                        Node.Parent.NodeColor = RBTreeNodeColor.Black;
                        Node.Parent.Parent.NodeColor = RBTreeNodeColor.Red;
                        RightRotate(Node.Parent.Parent);
                    }
                }//如果当前节点的父节点是右孩子.因为是对称情况，所以逻辑处理与前面类似.
                else
                {
                    //找到其叔父节点.
                    RBTreeNode theY = Node.Parent.Parent.Left;
                    if (theY.NodeColor == RBTreeNodeColor.Red)
                    {
                        Node.Parent.NodeColor = RBTreeNodeColor.Black;
                        theY.NodeColor = RBTreeNodeColor.Black;
                        Node.Parent.Parent.NodeColor = RBTreeNodeColor.Red;
                        Node = Node.Parent.Parent;
                    }
                    else
                    {
                        if (Node == Node.Parent.Left)
                        {
                            Node = Node.Parent;
                            RightRotate(Node);
                        }
                        Node.Parent.NodeColor = RBTreeNodeColor.Black;
                        Node.Parent.Parent.NodeColor = RBTreeNodeColor.Red;
                        LeftRotate(Node.Parent.Parent);
                    }
                }
            }
            //保证根节点为黑色.保证下次while循环可正常结束.
            Root.NodeColor = RBTreeNodeColor.Black;
        }
        /// <summary>
        /// 对一般情况，可以分4种情况考虑：
        /// 1）无孩子，则可直接删除；
        /// 2）只有左孩子:那么只要用左孩子替换当前节点即可;
        /// 3)只有右孩子：那么只要用左孩子替换当前节点即可;
        /// 4）如果有两个孩子：则必须找到当前节点的后继节点y.y要么没孩子，要么只有右孩子。可把把当前节点与y交互信息（key和卫星数据).
        /// 然后删除y（情况1..3).如果Y的颜色为黑色，因为y的删除破坏了红黑树性质，则需要重新整理，如果y是红色的，则删除不会改变
        /// 红黑树的性质.
        /// </summary>
        /// <param name="Node"></param>
        public void RBTreeDelete(RBTreeNode Node)
        {
            //默认theY指向当前节点.
            RBTreeNode theY = Node;
            //如果节点的左右子孩子都不为空，则需找到当前节点的后继节点,theY指向当前节点的后继节点.
            //注意这种情况下，当前节点的后继节点一定是右孩子的最小key节点.而且theY不可能有左孩子。
            if(Node.Right != NullNode && Node.Left != NullNode)
            {
                theY = GetSuccessor(Node);
            }
            RBTreeNode theX = NullNode;
            //theY.Left如果不为空，则theY就是指向Node节点，且没有右孩子.
            //如果theY没有左孩子，则theX指向theY的右孩子，注意theX也可能为空.
            if (theY.Left != NullNode)
            {
                theX = theY.Left;
            }
            else
            {
                theX = theY.Right;
            }
            //开始删除theY.
            theX.Parent = theY.Parent;
            //如果theY.Parent为空，则删除的是根节点，那么theX就变成了根节点。
            if (theY.Parent == NullNode)
            {
                Root = theX;
            }
            else
            {
                if (theY == theY.Parent.Left)
                {
                    theY.Parent.Left = theX;
                }
                else
                {
                    theY.Parent.Right = theX;
                }
            }
            //如果是theY不是指向当前节点，则表明是当前节点有两个孩子的情况，需交互数据.
            if (theY != Node)
            {
                Node.Key = theY.Key;
                //注意这里还序号拷贝卫星数据.
            }
            //如果删除的是黑节点，则必须重新整理（黑度发生变化，破坏了红黑树性质).
            if (theY.NodeColor == RBTreeNodeColor.Black)
            {
                RBTreeDelFixup(theX);
            }
        }
        /// <summary>
        /// 红黑书删除整理.因为删除的节点是黑色，则由此往上需要平衡黑度.
        /// </summary>
        /// <param name="Node"></param>
        public void RBTreeDelFixup(RBTreeNode Node)
        {
            while (Node != Root && Node.NodeColor == RBTreeNodeColor.Black)
            {
                //如果是左孩子
                if (Node == Node.Parent.Left)
                {
                    RBTreeNode theW = Node.Parent.Right;
                    //兄弟节点是红色，父节点必然是黑色，这时可以通过将兄弟节点置为黑色，父节点置为红色,
                    //然后左旋,右孩子保持黑度不变，左孩子黑度还是少1，便于继续处理。.
                    if (theW.NodeColor == RBTreeNodeColor.Red)
                    {
                        theW.NodeColor = RBTreeNodeColor.Black;
                        Node.Parent.NodeColor = RBTreeNodeColor.Red;
                        LeftRotate(Node.Parent);
                        theW = Node.Parent.Right;
                    }
                    //如果兄弟节点的左右孩子都是黑色，则可将兄弟节点置为红色，将需要平衡的节点上移后，还保持要平衡节点
                    //的黑度少1
                    if (theW.Left.NodeColor == RBTreeNodeColor.Black && theW.Right.NodeColor == RBTreeNodeColor.Black)
                    {
                        theW.NodeColor = RBTreeNodeColor.Red;
                        Node = Node.Parent;
                    }
                    else
                    {
                        //如果兄弟节点的孩子有一个是红色的，则可以通过调整就可以达到这种平衡.
                        if (theW.Right.NodeColor == RBTreeNodeColor.Black)
                        {
                            theW.Left.NodeColor = RBTreeNodeColor.Black;
                            theW.NodeColor = RBTreeNodeColor.Red;
                            RightRotate(theW);
                            theW = Node.Parent.Right;
                        }
                        theW.NodeColor = Node.Parent.NodeColor;
                        Node.Parent.NodeColor = RBTreeNodeColor.Black;
                        theW.Right.NodeColor = RBTreeNodeColor.Black;
                        LeftRotate(Node.Parent);
                        Node = Root;
                    }
                }
                else
                {
                    RBTreeNode theW = Node.Parent.Left;
                    //兄弟节点是红色，父节点必然是黑色，这时可以通过将兄弟节点置为黑色，父节点置为红色,
                    //然后右旋,左孩子保持黑度不变，右孩子黑度还是少1，便于继续处理。.
                    if (theW.NodeColor == RBTreeNodeColor.Red)
                    {
                        theW.NodeColor = RBTreeNodeColor.Black;
                        Node.Parent.NodeColor = RBTreeNodeColor.Red;
                        RightRotate(Node.Parent);
                        theW = Node.Parent.Left;
                    }
                    //如果兄弟节点的左右孩子都是黑色，则可将兄弟节点置为红色，将需要平衡的节点上移后，还保持要平衡节点
                    //的黑度少1
                    if (theW.Left.NodeColor == RBTreeNodeColor.Black && theW.Right.NodeColor == RBTreeNodeColor.Black)
                    {
                        theW.NodeColor = RBTreeNodeColor.Red;
                        Node = Node.Parent;
                    }
                    else
                    {
                        //如果兄弟节点的孩子有一个是红色的，则可以通过调整就可以达到这种平衡.
                        if (theW.Left.NodeColor == RBTreeNodeColor.Black)
                        {
                            theW.Left.NodeColor = RBTreeNodeColor.Black;
                            theW.NodeColor = RBTreeNodeColor.Red;
                            LeftRotate(theW);
                            theW = Node.Parent.Left;
                        }
                        theW.NodeColor = Node.Parent.NodeColor;
                        Node.Parent.NodeColor = RBTreeNodeColor.Black;
                        theW.Left.NodeColor = RBTreeNodeColor.Black;
                        RightRotate(Node.Parent);
                        Node = Root;
                    }

                }
                
            }
            Node.NodeColor = RBTreeNodeColor.Black;
        }
          所谓二叉树的旋转，其实可以这样理解，当前节点c,和其孩子l,r.看做是滑轮上的三个点，C在滑轮上的支点，l,r分别吊在
         滑轮的两边。左旋的结果就是右边得孩子到了滑轮支点，C向左下滑，右旋则相反，这种旋转，目的就是保持某种平衡。比如右边孩子
          如果重了，就会引起不平衡，就需要左旋，当然，因为要保持查找树的性质，左右孩子(l,r)的子节点需要做一定调整.
        /// <summary>
        /// 左旋，以当前节点和其右孩子为轴。
        /// </summary>
        /// <param name="Node"></param>
        private void LeftRotate(RBTreeNode Node)
        {
            RBTreeNode theY = Node.Right;
            Node.Right = theY.Left;
            theY.Left.Parent = Node;
            if (Node.Parent == NullNode)
            {
                Root = theY;
            }
            else if (Node == Node.Parent.Left)
            {
                Node.Parent.Left = theY;
            }
            else
            {
                Node.Parent.Right = theY;
            }
            theY.Left = Node;
            Node.Parent = theY;
            
        }
        /// <summary>
        /// 右旋，以当前节点和其左孩子为轴.
        /// </summary>
        /// <param name="Node"></param>
        private void RightRotate(RBTreeNode Node)
        {
            RBTreeNode theY = Node.Left;
            Node.Left = theY.Right;
            theY.Right.Parent = Node;
            if (Node.Parent == NullNode)
            {
                Root = theY;
            }
            else if (Node == Node.Parent.Left)
            {
                Node.Parent.Left = theY;
            }
            else
            {
                Node.Parent.Right = theY;
            }
            theY.Right = Node;
            Node.Parent = theY;
        }
        /// <summary>
        /// 查找树的节点.
        /// </summary>
        /// <param name="Key">关键值.</param>
        /// <returns></returns>
        public RBTreeNode TreeSearch(int Key)
        {
            RBTreeNode theX = Root;
            while (theX != NullNode && theX.Key != Key)
            {
                if (theX.Key < Key)
                {
                    theX = theX.Right;
                }
                else
                {
                    theX = theX.Left;
                }
            }
            return theX;
        }
        /// <summary>
        /// 获取以Node为根节点的子树的最小关键值节点
        /// </summary>
        /// <returns></returns>
        public RBTreeNode GetMinKeyNode(RBTreeNode Node)
        {
            RBTreeNode theX = Node;
            while (theX != NullNode)
            {
                theX = theX.Left;
            }
            return theX;
        }
        /// <summary>
        /// 获取以指定节点为根的子树的最大关键值节点
        /// </summary>
        /// <returns></returns>
        public RBTreeNode GetMaxKeyNode(RBTreeNode Node)
        {
            RBTreeNode theX = Node;
            while (theX != NullNode)
            {
                theX = theX.Right;
            }
            return theX;
        }
        /// <summary>
        /// 寻找指定节点的后继节点.
        /// </summary>
        /// <param name="node"></param>
        /// <returns></returns>
        public RBTreeNode GetSuccessor(RBTreeNode Node)
        {
            //如果当前节点右孩子不为空，则后续节点显然是右孩子子树的最小节点。
            if (Node.Right != NullNode)
            {
                return GetMinKeyNode(Node.Right);
            }
            RBTreeNode theF = Node.Parent;
            //如果当前节点的右孩子为空，则从其父节点开始判断，如果当前节点是其父节点的左孩子，则后续节点就是父节点.
            //如果是右孩子，则继续向上寻找，直到为空或者当前节点是其父节点的左孩子为止.
            while (theF != NullNode && Node != theF.Right)
            {
                Node = theF;
                theF = theF.Parent;
            }
            return theF;
        }
        /// <summary>
        /// 寻找指定节点的继节点.
        /// </summary>
        /// <param name="node"></param>
        /// <returns></returns>
        public RBTreeNode GetPredecessor(RBTreeNode Node)
        {
            //如果当前节点左孩子不为空，则后续节点显然是左孩子子树的最大节点。
            if (Node.Left != NullNode)
            {
                return GetMaxKeyNode(Node.Left);
            }
            RBTreeNode theF = Node.Parent;
            //如果当前节点的左孩子为空，则从其父节点开始判断，如果当前节点是其父节点的右孩子，则后续节点就是父节点.
            //如果是左孩子，则继续向上寻找，直到为空或者当前节点是其父节点的右孩子为止.
            while (theF != NullNode && Node != theF.Left)
            {
                Node = theF;
                theF = theF.Parent;
            }
            return theF;
        }
    }
    
}
*/