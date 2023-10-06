package com.hollow.saint.leetcode;

import java.util.*;

public class TreeTest {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	// 94 中序遍历
	List<Integer> ans = new ArrayList<>();
	public List<Integer> inorderTraversal(TreeNode root) {
		traverse(root);
		return ans;
	}

	public void traverse(TreeNode node) {
		if (null == node) {
			return;
		}
		traverse(node.left);
		ans.add(node.val);
		traverse(node.right);
	}

	// 100 相同的树
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (null == p && null == q) {
			return true;
		}
		if (null == p || null == q) {
			return false;
		}
		return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	// 102 二叉树层序遍历 bfs
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ans = new LinkedList<>();
		if (null == root) {
			return new LinkedList<>();
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			List<Integer> temp = new LinkedList<>();
			int n = queue.size();
			for (int i = 0; i < n; i++) {
				TreeNode cur = queue.poll();
				if (null == cur) {
					continue;
				}
				temp.add(cur.val);
				if (null != cur.left) {
					queue.offer(cur.left);
				}
				if (null != cur.right) {
					queue.offer(cur.right);
				}
			}
			ans.add(temp);
		}
		return ans;
	}

	// 103 二叉树锯齿形层序遍历
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ans = new LinkedList<>();
		if (null == root) {
			return new LinkedList<>();
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		boolean leftToRight = false;
		while (!queue.isEmpty()) {
			LinkedList<Integer> temp = new LinkedList<>();
			int n = queue.size();
			for (int i = 0; i < n; i++) {
				TreeNode cur = queue.poll();
				if (null == cur) {
					continue;
				}
				if (leftToRight) {
					temp.addFirst(cur.val);
				}
				else {
					temp.addLast(cur.val);
				}
				if (null != cur.left) {
					queue.offer(cur.left);
				}
				if (null != cur.right) {
					queue.offer(cur.right);
				}
			}
			leftToRight = !leftToRight;
			ans.add(new LinkedList<>(temp));
		}
		return ans;
	}

	// 107 二叉树层序遍历2
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		LinkedList<List<Integer>> ans = new LinkedList<>();
		if (null == root) {
			return new LinkedList<>();
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			List<Integer> temp = new LinkedList<>();
			int n = queue.size();
			for (int i = 0; i < n; i++) {
				TreeNode cur = queue.poll();
				if (null == cur) {
					continue;
				}
				temp.add(cur.val);
				if (null != cur.left) {
					queue.offer(cur.left);
				}
				if (null != cur.right) {
					queue.offer(cur.right);
				}
			}
			ans.addFirst(temp);
		}
		return ans;
	}

	// 104 二叉树最大深度
	public int maxDepth(TreeNode root) {
		if (null == root) {
			return 0;
		}
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		return Math.max(left, right) + 1;
	}

	// 105 从前序和中序遍历序列构造二叉树
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return build(preorder, 0, preorder.length - 1,
				inorder, 0, inorder.length - 1);
	}

	public TreeNode build(int[] preorder, int preStart, int preEnd,
	                      int[] inorder, int inStart, int inEnd) {
		if (preStart > preEnd) {
			return null;
		}
		// 根节点
		TreeNode root = new TreeNode(preorder[preStart]);
		// 根节点在中序遍历中的索引
		int index = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				index = i;
				break;
			}
		}
		// 计算左子树大小
		int leftSize = index - inStart;
		// 构造左子树
		root.left = build(preorder, preStart + 1, preStart + leftSize,
				inorder, inStart, index - 1);
		// 构造右子树
		root.right = build(preorder, preStart + leftSize + 1, preEnd,
				inorder, index + 1, inEnd);
		return root;
	}

	// 654 最大二叉树
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		return builder(nums, 0, nums.length - 1);
	}

	public TreeNode builder(int[] nums, int start, int end) {
		if (start > end) {
			return null;
		}
		int maxIndex = 0;
		int max = Integer.MIN_VALUE;
		for (int i = start; i <= end; i++) {
			if (nums[i] > max) {
				maxIndex = i;
				max = nums[i];
			}
		}
		TreeNode root = new TreeNode(max);
		root.left = builder(nums, start, maxIndex - 1);
		root.right = builder(nums, maxIndex + 1, end);
		return root;
	}

	// 111 二叉树最小深度
	public int minDepth(TreeNode root) {
		if (null == root) {
			return 0;
		}
		int ans = 1;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int n = queue.size();
			for (int i = 0; i < n; i++) {
				TreeNode cur = queue.poll();
				if (null == cur) {
					continue;
				}
				if (null == cur.left && null == cur.right) {
					return ans;
				}
				if (null != cur.left) {
					queue.offer(cur.left);
				}
				if (null != cur.right) {
					queue.offer(cur.right);
				}
			}
			ans++;
		}
		return ans;
	}

	// 114 二叉树展开为链表
	public void flatten(TreeNode root) {
		if (null == root) {
			return;
		}
		// 后序
		flatten(root.left);
		flatten(root.right);
		TreeNode left = root.left;
		TreeNode right = root.right;
		root.left = null;
		root.right = left;
		TreeNode tmp = root;
		while (null != tmp.right) {
			tmp = tmp.right;
		}
		tmp.right = right;
	}

	// 226 反转二叉树
	public TreeNode invertTree(TreeNode root) {
		if (null == root) {
			return null;
		}
		// 前序
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;
		invertTree(root.left);
		invertTree(root.right);
		return root;
	}

	// Definition for a Node.
//	static class Node {
//		public int val;
//		public Node left;
//		public Node right;
//		public Node next;
//
//		public Node() {
//		}
//
//		public Node(int _val) {
//			val = _val;
//		}
//
//		public Node(int _val, Node _left, Node _right, Node _next) {
//			val = _val;
//			left = _left;
//			right = _right;
//			next = _next;
//		}
//	}
//
//	// 116 填充每个节点下一个右侧指针
//	public Node connect(Node root) {
//		if (null == root) {
//			return null;
//		}
//		Queue<Node> queue = new LinkedList<>();
//		queue.offer(root);
//		while (!queue.isEmpty()) {
//			int n = queue.size();
//			for (int i = 0; i < n; i++) {
//				Node node = queue.poll();
//				if (null == node) {
//					continue;
//				}
//				if (i < n - 1) {
//					node.next = queue.peek();
//				}
//				if (null != node.left) {
//					queue.offer(node.left);
//				}
//				if (null != node.right) {
//					queue.offer(node.right);
//				}
//			}
//		}
//		return root;
//	}

	// 144 二叉树的前序遍历
	List<Integer> preAns = new LinkedList<>();
	public List<Integer> preorderTraversal(TreeNode root) {
		if (null == root) {
			return preAns;
		}
		preTraverse(root);
		return preAns;
	}

	public void preTraverse(TreeNode node) {
		if (null == node) {
			return;
		}
		preAns.add(node.val);
		preTraverse(node.left);
		preTraverse(node.right);
	}

	// 222 完全二叉树节点个数
	public int countNodes(TreeNode root) {
		TreeNode left = root, right = root;
		int leftHeight = 0;
		while (null != left) {
			leftHeight++;
			left = left.left;
		}
		int rightHeight = 0;
		while (null != right) {
			rightHeight++;
			right = right.right;
		}
		if (leftHeight == rightHeight) {
			return (int) Math.pow(2, leftHeight) - 1;
		}
		return countNodes(root.left) + countNodes(root.right) + 1;
	}

	// 236 最近的公共祖先
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (null == root) {
			return null;
		}
		if (root == p || root == q) {
			return root;
		}
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if (null != left && null != right) {
			return root;
		}
		if (null == left && null == right) {
			return null;
		}
		return null == left ? right : left;
	}

	// 297 二叉树序列化和反序列化
	public class Codec {

		StringBuilder sb;

		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {
			sb = new StringBuilder();
			traverse(root);
			return sb.toString();
		}

		public void traverse(TreeNode node) {
			if (null == node) {
				sb.append(",").append("n");
				return;
			}
			sb.append(",").append(node.val);
			traverse(node.left);
			traverse(node.right);
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(String data) {
			if (data.length() == 0) {
				return null;
			}
			LinkedList<String> list = new LinkedList<>(Arrays.asList(data.substring(1).split(",")));
			return helper(list);
		}

		public TreeNode helper(LinkedList<String> list) {
			if (list.isEmpty()) {
				return null;
			}
			String val = list.removeFirst();
			if ("n".equals(val)) {
				return null;
			}
			TreeNode root = new TreeNode(Integer.parseInt(val));
			root.left = helper(list);
			root.right = helper(list);
			return root;
		}
	}

	// 501 二叉搜索树中的众数
	List<Integer> mode = new ArrayList<>();
	TreeNode prev = null;
	int curMax;
	int modeMax;
	public int[] findMode(TreeNode root) {
		inHelper(root);
		int[] res = new int[mode.size()];
		for (int i = 0; i < mode.size(); i++) {
			res[i] = mode.get(i);
		}
		return res;
	}

	public void inHelper(TreeNode node) {
		if (null == node) {
			return;
		}
		inHelper(node.left);
		// 中序
		if (null == prev) {
			curMax = 1;
			modeMax = 1;
			mode.add(node.val);
		}
		else {
			if (node.val == prev.val) {
				curMax++;
				if (curMax == modeMax) {
					mode.add(node.val);
				}
				else if (curMax > modeMax) {
					modeMax = curMax;
					mode.clear();
					mode.add(node.val);
				}
			}
			if (node.val != prev.val) {
				curMax = 1;
				if (curMax == modeMax) {
					mode.add(node.val);
				}
			}
		}
		prev = node;
		inHelper(node.right);
	}

	// 543 二叉树的最大直径
	int maxDiameter = 0;
	public int diameterOfBinaryTree(TreeNode root) {
		maxDiameter(root);
		return maxDiameter;
	}

	public int maxDiameter(TreeNode node) {
		if (null == node) {
			return 0;
		}
		int left = maxDiameter(node.left);
		int right = maxDiameter(node.right);
		maxDiameter = Math.max(maxDiameter, left + right);
		return 1 + Math.max(left, right);
	}

	// Definition for a Node.
	class Node {
	    public int val;
	    public List<Node> children;

	    public Node() {}

	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, List<Node> _children) {
	        val = _val;
	        children = _children;
	    }
	}

	// 559 N叉树的最大深度
	public int maxDepth(Node root) {
		if (null == root) {
			return 0;
		}
		int nMaxDepth = 0;
		for (Node child : root.children) {
			nMaxDepth = Math.max(nMaxDepth, maxDepth(child));
		}
		return 1 + nMaxDepth;
	}

	// 589 N叉树的前序遍历
	List<Integer> nPreAns = new ArrayList<>();
	public List<Integer> preorder(Node root) {
		nPreTraverse(root);
		return nPreAns;
	}

	public void nPreTraverse(Node node) {
		if (null == node) {
			return;
		}
		nPreAns.add(node.val);
		for (Node child : node.children) {
			nPreTraverse(child);
		}
	}

	// 590 N叉树的后序遍历
	List<Integer> nPostAns = new ArrayList<>();
	public List<Integer> postorder(Node root) {
		nPostTraverse(root);
		return nPostAns;
	}

	public void nPostTraverse(Node node) {
		if (null == node) {
			return;
		}
		for (Node child : node.children) {
			nPostTraverse(child);
		}
		nPostAns.add(node.val);
	}

	// 652 寻找重复的子树
	List<TreeNode> subList = new ArrayList<>();
	Map<String, Integer> subFreq = new HashMap<>();
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		subHelper(root);
		return subList;
	}

	public String subHelper(TreeNode node) {
		if (null == node) {
			return "#";
		}
		String left = subHelper(node.left);
		String right = subHelper(node.right);
		String sub = left + "," + right + "," + node.val;
		int freq = subFreq.getOrDefault(sub, 0);
		if (freq == 1) {
			subList.add(node);
		}
		subFreq.put(sub, freq + 1);
		return sub;
	}

	// 965 单值二叉树
	public boolean isUnivalTree(TreeNode root) {
		if (null == root) {
			return true;
		}
		if (null == root.left && null == root.right) {
			return true;
		}
		if (null != root.left && root.val != root.left.val) {
			return false;
		}
		if (null != root.right && root.val != root.right.val) {
			return false;
		}
		boolean left = isUnivalTree(root.left);
		boolean right = isUnivalTree(root.right);
		return left && right;
	}

	// 98 验证二叉搜索树
	long bPrev = Long.MIN_VALUE;
	public boolean isValidBST(TreeNode root) {
		return bInTraverse(root);
	}

	public boolean bInTraverse(TreeNode node) {
		if (null == node) {
			return true;
		}
		if (!bInTraverse(node.left)) {
			return false;
		}
		// 中序
		if (node.val < bPrev) {
			return false;
		}
		bPrev = node.val;
		return bInTraverse(node.right);
	}

	// 700 二叉搜索树中的搜索
	public TreeNode searchBST(TreeNode root, int val) {
		if (null == root) {
			return null;
		}
		if (root.val == val) {
			return root;
		}
		else if (root.val > val) {
			return searchBST(root.left, val);
		}
		else if (root.val < val) {
			return searchBST(root.right, val);
		}
		return null;
	}

	// 701 二叉搜索树中的插入
	public TreeNode insertIntoBST(TreeNode root, int val) {
		if (null == root) {
			return new TreeNode(val);
		}
		if (root.val > val) {
			root.left = insertIntoBST(root.left, val);
		}
		else if (root.val < val) {
			root.right = insertIntoBST(root.right, val);
		}
		return root;
	}

	// 450 二叉搜索树中的删除
	public TreeNode deleteNode(TreeNode root, int key) {
		if (null == root) {
			return null;
		}
		if (root.val > key) {
			root.left = deleteNode(root.left, key);
		}
		else if (root.val < key) {
			root.right = deleteNode(root.right, key);
		}
		// 删除
		else {
			if (null == root.left) return root.right;
			if (null == root.right) return root.left;
			// 左右子树都不为空
			TreeNode tmp = root.right;
			while (null != tmp.left) {
				tmp = tmp.left;
			}
			tmp.left = root.left;
			root = root.right;
		}
		return root;
	}

	// 230 二叉搜索树中第k小的元素
	int kCount = 0;
	int kAns = 0;
	public int kthSmallest(TreeNode root, int k) {
		kInTraverse(root, k);
		return kAns;
	}

	public void kInTraverse(TreeNode node, int k) {
		if (null == node) {
			return;
		}
		kInTraverse(node.left, k);
		kCount++;
		if (k == kCount) {
			kAns = node.val;
			return;
		}
		kInTraverse(node.right, k);
	}

	// 538 二叉搜索树转化为累加树
	int cSum = 0;
	public TreeNode convertBST(TreeNode root) {
		cInTraverse(root);
		return root;
	}

	public void cInTraverse(TreeNode node) {
		if (null == node) {
			return;
		}
		cInTraverse(node.right);
		cSum += node.val;
		node.val = cSum;
		cInTraverse(node.left);
	}

	// 530 二叉搜素树最小值差
	int mPrev = 0;
	int mAns = Integer.MAX_VALUE;
	public int getMinimumDifference(TreeNode root) {
		mInTraverse(root);
		return mAns;
	}

	public void mInTraverse(TreeNode node) {
		if (null == node) {
			return;
		}
		mInTraverse(node.left);
		mAns = Math.min(mAns, node.val - mPrev);
		mPrev = node.val;
		mInTraverse(node.right);
	}

	// 1373 二叉搜索子树最大和
	int bMax = 0;
	public int maxSumBST(TreeNode root) {
		bPostTraverse(root);
		return bMax;
	}

	// 返回一个数组 大小为4
	// 0 当前节点为根的二叉树是否为二叉搜索树 0 是 1 否
	// 1 当前节点为根的二叉树的最小值
	// 2 当前节点为根的二叉树的最大值
	// 3 当前节点为根的二叉树的节点和
	public int[] bPostTraverse(TreeNode node) {
		if (null == node) {
			return new int[] {1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
		}
		int[] left = bPostTraverse(node.left);
		int[] right = bPostTraverse(node.right);
		// 后序
		int[] res = new int[4];
		if (left[0] == 1 && right[0] == 1 && node.val > left[2] && node.val < right[1]) {
			res[0] = 1;
			res[1] = Math.min(left[1], node.val);
			res[2] = Math.max(right[2], node.val);
			res[3] = left[3] + right[3] + node.val;
			bMax = Math.max(bMax, res[3]);
		}
		return res;
	}

	private static int isPresent(TreeNode root, int val){
		if (null == root) {
			return 0;
		}
		if (root.val == val) {
			return 1;
		}
		else if (root.val < val) {
			isPresent(root.right, val);
		}
		else if (root.val > val) {
			isPresent(root.left, val);
		}
		return 0;
	}


}
