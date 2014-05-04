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
    /// �ڵ���ɫö��
    /// </summary>
    public enum RBTreeNodeColor
    {
        Red,
        Black
    }
    /// <summary>
    /// ������ڵ�
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
    /// �����
    /// </summary>
    public class RBTree
    {
        /// <summary>
        /// �սڵ㣬�䵱�ڱ�
        /// </summary>
        public static readonly RBTreeNode NullNode=new RBTreeNode();
        /// <summary>
        /// ��̬���캯�����ڱ�Ϊ��ɫ.
        /// </summary>
        static RBTree()
        {
            NullNode.NodeColor = RBTreeNodeColor.Black;
        }
        /// <summary>
        /// ���ڵ㣬Ĭ��ΪNullNode.
        /// </summary>
        public RBTreeNode Root = NullNode;
        /// <summary>
        /// ����ڵ�,������������еĽڵ�key����ͬ.
        /// </summary>
        /// <param name="Node"></param>
        public void InsertNode(RBTreeNode Node)
        {
            ///Ѱ�Ҳ���ڵ�Ĳ���㣨���ڵ㣩theY.TheX��ѭ���м����.
            RBTreeNode theY = NullNode;
            RBTreeNode theX = Root;
            //ע�⣬�����Ϊ�գ����ҵ��Ĳ���ڵ�һ������ײ��ڽڵ㣬���п��ֽڵ�(�ڱ��ڵ�).
            //��Ҳ�Ǻ�������Ǹ߶�ƽ������һ����Ҫԭ��.���������˲��룬�����жϲ�ȱ.
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
            //������ڵ�ĸ��ڵ�ָ��theY.
            Node.Parent = theY;
            //���theYΪ�գ���ԭ���ǿ��������²���Ľڵ��Ǹ��ڵ�.
            if (theY == NullNode)
            {
                Root = Node;
            }
            else
            {
                //����и��ڵ㣬��theY��Key�Ƚϣ���������Ϊ���ӽڵ㣬С����
                //��Ϊ��ڵ�.
                if (Node.Key > theY.Key)
                {
                    Node.Parent = theY.Right;
                }
                else
                {
                    Node.Parent = theY.Left;
                }
            }
            //��ʼ����Ľڵ㶼�ڷǿ�Ҷ�ӽڵ��ϣ����սڵ���Ϊ�������ӽڵ�.
            Node.Right = NullNode;
            Node.Left = NullNode;
            //��ɫ��Ϊ�죬Ȼ������.
            Node.NodeColor = RBTreeNodeColor.Red;
            RBInsertFixup(Node);
        }
        /// <summary>
        /// ������Ĳ��������Ա��ֺ�������ʣ��Ǻ켴�ף�����Ҷ�Ӻڣ����ڽڵ㲻��ͬΪ�죻
        /// ���дӸ���Ҷ��·���ϵĺڽڵ�(�ڶ�)��ͬ.���������ֻ���Ǹ��ӽڵ�䣬���ϵĶ�����
        /// ��ڵ���������ӽڵ㶼�Ǻڵģ�����ǵȼ۵�.�Ӹ���Ҷ�ӽڵ㿴��������·������·��
        /// �Ϻ�ڵ㲻������.
        /// </summary>
        /// <param name="Node"></param>
        private void RBInsertFixup(RBTreeNode Node)
        {
            //��Ϊ��ǰ�ڵ�Ϊ��ɫ����������������������ǰ�ڵ�ĸ��ڵ�Ϊ��ɫ����Υ���˺���������ʣ�
            //����Ҫ��������ֱ����ǰ����ڵ�ĸ��ڵ��Ǻ�ɫΪֹ.����һ�����н���������
            //��Ϊ���ڵ�϶�Ϊ�ڡ�
            while (Node.Parent.NodeColor == RBTreeNodeColor.Red)
            {
                //������ڵ�Ϊ��ɫ����ү�ڵ��Ϊ��ɫ����������ʣ���
                //�����ǰ�ڵ�ĸ��ڵ�������
                if (Node.Parent == Node.Parent.Parent.Left)
                {
                    //�ҵ����常�ڵ�.
                    RBTreeNode theY = Node.Parent.Parent.Right;
                    //����常�ڵ��Ǻ�ɫ�ڵ㣬��ү�ڵ�Ϊ��ɫ�ڵ㡣��ʽ����ү�ڵ�Ϊ�ڣ������ڵ�Ϊ�죬���ڵ�ǰ�ڵ�Ϊ�죬
                    //���ڵ�����Ϊ��ɫ���������ָı䲻��Ӱ��ڶ�(���дӸ���Ҷ�ӽڵ��·���кڽڵ�����ȣ�����ô�ı�Ĳ���
                    //���ǽ�ү�ڵ��Ϊ��ɫ�������ڵ��Ϊ��ɫ�����ָı���Ȼû�иı�·���ϵĺڶȡ�����Ϊү�ڵ��Ϊ�˺�ɫ������
                    //�ᵼ�º�������ʵĸı�,����Ҫ������������轫����ڵ����Ƶ�ү�ڵ㣬��������
                    if (theY.NodeColor == RBTreeNodeColor.Red)
                    {
                        Node.Parent.NodeColor = RBTreeNodeColor.Black;
                        theY.NodeColor = RBTreeNodeColor.Black;
                        Node.Parent.Parent.NodeColor = RBTreeNodeColor.Red;
                        Node = Node.Parent.Parent;
                    }
                    //����常�ڵ��Ǻ�ɫ��ү�ڵ�ҲΪ��ɫ�����ڵ���ΪҪ�ĳɺڽڵ㣬�������˵�ǰ�ڵ�·���ϵĺڶȡ���˴������΢����Щ��
                    else
                    {
                        //�����ǰ�ڵ����Һ��ӣ�����������Ϊ���ǵ�ǰ�ڵ�͸��ڵ㣬��Ϊ��ɫ�������������ı�ڶȣ���ܹؼ���
                        if (Node == Node.Parent.Right)
                        {
                            Node = Node.Parent;
                            LeftRotate(Node);
                        }
                        //�����ڵ��Ϊ�ڽڵ㣬ү�ڵ��Ϊ��ɫ�ڵ㣬��ǰ�ڵ�·���ϵĺڶ�û�䣬���常·���ϵĺڶȱ�����1��Ϊ�˱��ֺڶȲ��䣬�����Ҫ����
                        //��ʵ���ǽ����ڵ�������ү�ڵ㣬ԭ����ү�ڵ㽵Ϊ�常�ڵ㣬�����ڶȾ�ƽ���ˡ�
                        Node.Parent.NodeColor = RBTreeNodeColor.Black;
                        Node.Parent.Parent.NodeColor = RBTreeNodeColor.Red;
                        RightRotate(Node.Parent.Parent);
                    }
                }//�����ǰ�ڵ�ĸ��ڵ����Һ���.��Ϊ�ǶԳ�����������߼�������ǰ������.
                else
                {
                    //�ҵ����常�ڵ�.
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
            //��֤���ڵ�Ϊ��ɫ.��֤�´�whileѭ������������.
            Root.NodeColor = RBTreeNodeColor.Black;
        }
        /// <summary>
        /// ��һ����������Է�4��������ǣ�
        /// 1���޺��ӣ����ֱ��ɾ����
        /// 2��ֻ������:��ôֻҪ�������滻��ǰ�ڵ㼴��;
        /// 3)ֻ���Һ��ӣ���ôֻҪ�������滻��ǰ�ڵ㼴��;
        /// 4��������������ӣ�������ҵ���ǰ�ڵ�ĺ�̽ڵ�y.yҪôû���ӣ�Ҫôֻ���Һ��ӡ��ɰѰѵ�ǰ�ڵ���y������Ϣ��key����������).
        /// Ȼ��ɾ��y�����1..3).���Y����ɫΪ��ɫ����Ϊy��ɾ���ƻ��˺�������ʣ�����Ҫ�����������y�Ǻ�ɫ�ģ���ɾ������ı�
        /// �����������.
        /// </summary>
        /// <param name="Node"></param>
        public void RBTreeDelete(RBTreeNode Node)
        {
            //Ĭ��theYָ��ǰ�ڵ�.
            RBTreeNode theY = Node;
            //����ڵ�������Ӻ��Ӷ���Ϊ�գ������ҵ���ǰ�ڵ�ĺ�̽ڵ�,theYָ��ǰ�ڵ�ĺ�̽ڵ�.
            //ע����������£���ǰ�ڵ�ĺ�̽ڵ�һ�����Һ��ӵ���Сkey�ڵ�.����theY�����������ӡ�
            if(Node.Right != NullNode && Node.Left != NullNode)
            {
                theY = GetSuccessor(Node);
            }
            RBTreeNode theX = NullNode;
            //theY.Left�����Ϊ�գ���theY����ָ��Node�ڵ㣬��û���Һ���.
            //���theYû�����ӣ���theXָ��theY���Һ��ӣ�ע��theXҲ����Ϊ��.
            if (theY.Left != NullNode)
            {
                theX = theY.Left;
            }
            else
            {
                theX = theY.Right;
            }
            //��ʼɾ��theY.
            theX.Parent = theY.Parent;
            //���theY.ParentΪ�գ���ɾ�����Ǹ��ڵ㣬��ôtheX�ͱ���˸��ڵ㡣
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
            //�����theY����ָ��ǰ�ڵ㣬������ǵ�ǰ�ڵ����������ӵ�������轻������.
            if (theY != Node)
            {
                Node.Key = theY.Key;
                //ע�����ﻹ��ſ�����������.
            }
            //���ɾ�����Ǻڽڵ㣬��������������ڶȷ����仯���ƻ��˺��������).
            if (theY.NodeColor == RBTreeNodeColor.Black)
            {
                RBTreeDelFixup(theX);
            }
        }
        /// <summary>
        /// �����ɾ������.��Ϊɾ���Ľڵ��Ǻ�ɫ�����ɴ�������Ҫƽ��ڶ�.
        /// </summary>
        /// <param name="Node"></param>
        public void RBTreeDelFixup(RBTreeNode Node)
        {
            while (Node != Root && Node.NodeColor == RBTreeNodeColor.Black)
            {
                //���������
                if (Node == Node.Parent.Left)
                {
                    RBTreeNode theW = Node.Parent.Right;
                    //�ֵܽڵ��Ǻ�ɫ�����ڵ��Ȼ�Ǻ�ɫ����ʱ����ͨ�����ֵܽڵ���Ϊ��ɫ�����ڵ���Ϊ��ɫ,
                    //Ȼ������,�Һ��ӱ��ֺڶȲ��䣬���ӺڶȻ�����1�����ڼ�������.
                    if (theW.NodeColor == RBTreeNodeColor.Red)
                    {
                        theW.NodeColor = RBTreeNodeColor.Black;
                        Node.Parent.NodeColor = RBTreeNodeColor.Red;
                        LeftRotate(Node.Parent);
                        theW = Node.Parent.Right;
                    }
                    //����ֵܽڵ�����Һ��Ӷ��Ǻ�ɫ����ɽ��ֵܽڵ���Ϊ��ɫ������Ҫƽ��Ľڵ����ƺ󣬻�����Ҫƽ��ڵ�
                    //�ĺڶ���1
                    if (theW.Left.NodeColor == RBTreeNodeColor.Black && theW.Right.NodeColor == RBTreeNodeColor.Black)
                    {
                        theW.NodeColor = RBTreeNodeColor.Red;
                        Node = Node.Parent;
                    }
                    else
                    {
                        //����ֵܽڵ�ĺ�����һ���Ǻ�ɫ�ģ������ͨ�������Ϳ��Դﵽ����ƽ��.
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
                    //�ֵܽڵ��Ǻ�ɫ�����ڵ��Ȼ�Ǻ�ɫ����ʱ����ͨ�����ֵܽڵ���Ϊ��ɫ�����ڵ���Ϊ��ɫ,
                    //Ȼ������,���ӱ��ֺڶȲ��䣬�Һ��ӺڶȻ�����1�����ڼ�������.
                    if (theW.NodeColor == RBTreeNodeColor.Red)
                    {
                        theW.NodeColor = RBTreeNodeColor.Black;
                        Node.Parent.NodeColor = RBTreeNodeColor.Red;
                        RightRotate(Node.Parent);
                        theW = Node.Parent.Left;
                    }
                    //����ֵܽڵ�����Һ��Ӷ��Ǻ�ɫ����ɽ��ֵܽڵ���Ϊ��ɫ������Ҫƽ��Ľڵ����ƺ󣬻�����Ҫƽ��ڵ�
                    //�ĺڶ���1
                    if (theW.Left.NodeColor == RBTreeNodeColor.Black && theW.Right.NodeColor == RBTreeNodeColor.Black)
                    {
                        theW.NodeColor = RBTreeNodeColor.Red;
                        Node = Node.Parent;
                    }
                    else
                    {
                        //����ֵܽڵ�ĺ�����һ���Ǻ�ɫ�ģ������ͨ�������Ϳ��Դﵽ����ƽ��.
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
          ��ν����������ת����ʵ����������⣬��ǰ�ڵ�c,���亢��l,r.�����ǻ����ϵ������㣬C�ڻ����ϵ�֧�㣬l,r�ֱ����
         ���ֵ����ߡ������Ľ�������ұߵú��ӵ��˻���֧�㣬C�����»����������෴��������ת��Ŀ�ľ��Ǳ���ĳ��ƽ�⡣�����ұߺ���
          ������ˣ��ͻ�����ƽ�⣬����Ҫ��������Ȼ����ΪҪ���ֲ����������ʣ����Һ���(l,r)���ӽڵ���Ҫ��һ������.
        /// <summary>
        /// �������Ե�ǰ�ڵ�����Һ���Ϊ�ᡣ
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
        /// �������Ե�ǰ�ڵ��������Ϊ��.
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
        /// �������Ľڵ�.
        /// </summary>
        /// <param name="Key">�ؼ�ֵ.</param>
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
        /// ��ȡ��NodeΪ���ڵ����������С�ؼ�ֵ�ڵ�
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
        /// ��ȡ��ָ���ڵ�Ϊ�������������ؼ�ֵ�ڵ�
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
        /// Ѱ��ָ���ڵ�ĺ�̽ڵ�.
        /// </summary>
        /// <param name="node"></param>
        /// <returns></returns>
        public RBTreeNode GetSuccessor(RBTreeNode Node)
        {
            //�����ǰ�ڵ��Һ��Ӳ�Ϊ�գ�������ڵ���Ȼ���Һ�����������С�ڵ㡣
            if (Node.Right != NullNode)
            {
                return GetMinKeyNode(Node.Right);
            }
            RBTreeNode theF = Node.Parent;
            //�����ǰ�ڵ���Һ���Ϊ�գ�����丸�ڵ㿪ʼ�жϣ������ǰ�ڵ����丸�ڵ�����ӣ�������ڵ���Ǹ��ڵ�.
            //������Һ��ӣ����������Ѱ�ң�ֱ��Ϊ�ջ��ߵ�ǰ�ڵ����丸�ڵ������Ϊֹ.
            while (theF != NullNode && Node != theF.Right)
            {
                Node = theF;
                theF = theF.Parent;
            }
            return theF;
        }
        /// <summary>
        /// Ѱ��ָ���ڵ�ļ̽ڵ�.
        /// </summary>
        /// <param name="node"></param>
        /// <returns></returns>
        public RBTreeNode GetPredecessor(RBTreeNode Node)
        {
            //�����ǰ�ڵ����Ӳ�Ϊ�գ�������ڵ���Ȼ���������������ڵ㡣
            if (Node.Left != NullNode)
            {
                return GetMaxKeyNode(Node.Left);
            }
            RBTreeNode theF = Node.Parent;
            //�����ǰ�ڵ������Ϊ�գ�����丸�ڵ㿪ʼ�жϣ������ǰ�ڵ����丸�ڵ���Һ��ӣ�������ڵ���Ǹ��ڵ�.
            //��������ӣ����������Ѱ�ң�ֱ��Ϊ�ջ��ߵ�ǰ�ڵ����丸�ڵ���Һ���Ϊֹ.
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