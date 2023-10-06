package com.hollow.saint.leetcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ListNodeTest {

	public static class ListNode {
	    int val;
	    ListNode next;
	    ListNode() {}
	    ListNode(int val) { this.val = val; }
	    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	// 2 两数相加
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode p1 = l1, p2 = l2;
		ListNode dummy = new ListNode(-1);
		ListNode p = dummy;
		int add = 0;
		while (null != p1 || null != p2 || add > 0) {
			int val = add;
			if (null != p1) {
				val += p1.val;
				p1 = p1.next;
			}
			if (null != p2) {
				val += p2.val;
				p2 = p2.next;
			}
			add = val / 10;
			p.next = new ListNode(val % 10);
			p = p.next;
		}
		return dummy.next;
	}

	// 19 删除链表倒数第N个节点
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode p1 = dummy, p2 = dummy;
		for (int i = 0; i < n; i++) {
			p1 = p1.next;
		}
		while (null != p1.next) {
			p1 = p1.next;
			p2 = p2.next;
		}
		p2.next = p2.next.next;
		return dummy.next;
	}

	// 21 合并两个有序链表
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode dummy = new ListNode(-1);
		ListNode p = dummy;
		ListNode p1 = list1, p2 = list2;
		while (null != p1 && null != p2) {
			if (p1.val <= p2.val) {
				p.next = p1;
				p1 = p1.next;
			}
			else {
				p.next = p2;
				p2 = p2.next;
			}
			p = p.next;
		}
		p.next = null == p1 ? p2 : p1;
		return dummy.next;
	}

	// 合并k个有序链表
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		ListNode dummy = new ListNode(-1);
		ListNode p = dummy;
		// 优先队列
		PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				// 最小堆
				return o1.val - o2.val;
			}
		});
		for (ListNode node : lists) {
			if (null != node) {
				queue.offer(node);
			}
		}
		while (!queue.isEmpty()) {
			ListNode tmp = queue.poll();
			p.next = tmp;
			if (null != tmp.next) {
				queue.offer(tmp.next);
			}
			p = p.next;
		}
		return dummy.next;
	}

	// 141 环形链表
	public boolean hasCycle(ListNode head) {
		if (null == head) {
			return false;
		}
		ListNode slow = head, fast = head;
		while (null != fast && null != fast.next) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	// 142 环形链表2
	public ListNode detectCycle(ListNode head) {
		if (null == head) {
			return null;
		}
		ListNode slow = head, fast = head;
		boolean hasCycle = false;
		while (null != fast && null != fast.next) {
			slow = slow.next;
			fast = fast.next.next;
			// 有环
			if (slow == fast) {
				hasCycle = true;
				break;
			}
		}
		if (!hasCycle) {
			return null;
		}
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

	// 160 链表相交
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode p1 = headA, p2 = headB;
		while (null != p1 || null != p2) {
			if (null == p1) {
				p1 = headB;
			}
			if (null == p2) {
				p2 = headA;
			}
			if (p1 == p2) {
				return p1;
			}
			p1 = p1.next;
			p2 = p2.next;
		}
		return null;
	}

	// 876 链表中间的节点
	public ListNode middleNode(ListNode head) {
		ListNode slow = head, fast = head;
		while (null != fast && null != fast.next) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

//	// 206 翻转链表
//	public ListNode reverseList(ListNode head) {
//		if (null == head || null == head.next) {
//			return head;
//		}
//		ListNode last = reverseList(head.next);
//		head.next.next = head;
//		head.next = null;
//		return last;
//	}
//
//	// 翻转链表前n个节点
//	ListNode successor = null;
//	public ListNode reverseNList(ListNode head, int n) {
//		if (n == 1) {
//			successor = head.next;
//			return head;
//		}
//		ListNode last = reverseNList(head.next, n - 1);
//		head.next.next = head;
//		head.next = successor;
//		return last;
//	}
//
//	// 92 翻转链表区间
//	public ListNode reverseBetween(ListNode head, int left, int right) {
//		if (left == 1) {
//			return reverseNList(head, right);
//		}
//		head.next = reverseBetween(head.next,left - 1, right - 1);
//		return head;
//	}

	// 206 翻转链表 双指针
	public ListNode reverseList(ListNode head) {
		ListNode pre = null;
		ListNode cur = head;
		while (null != cur) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

	// 92 翻转链表区间 头插法
	public ListNode reverseBetween(ListNode head, int left, int right) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy, cur = dummy.next;
		for (int i = 1; i < left; i++) {
			pre = pre.next;
			cur = cur.next;
		}
		for (int i = 0; i < right - left; i++) {
			ListNode next = cur.next;
			cur.next = cur.next.next;
			next.next = pre.next;
			pre.next = next;
		}
		return dummy.next;
	}

	// 25 k个一组翻转链表
	public ListNode reverseKGroup(ListNode head, int k) {
		if (k == 1) return head;
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode left = dummy, right = head;
		while (null != right) {
			int cnt = 0;
			while (cnt < k && null != right) {
				cnt++;
				right = right.next;
			}
			if (cnt < k) break;
			ListNode tmp = left.next;
			reverseK(left, right);
			left = tmp;
		}
		return dummy.next;
	}

	// 翻转(left,right)区间内的节点，开区间不含left,right
	public void reverseK(ListNode left, ListNode right) {
		if (left == right || left.next == right) return;
		ListNode pre = null, cur = left.next;
		while (cur != right) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		left.next.next = right;
		left.next = pre;
	}

	// 83 删除有序链表中的重复元素
	public ListNode deleteDuplicates(ListNode head) {
		if (null == head) {
			return null;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = head, cur = head.next;
		while (null != cur) {
			if (pre.val != cur.val) {
				pre = pre.next;
				cur = cur.next;
				continue;
			}
			// 删除
			pre.next = cur.next;
			cur = cur.next;
		}
		return dummy.next;
	}

//	// 234 回文链表 拷贝到数组使用双指针
//	public boolean isPalindrome(ListNode head) {
//		List<Integer> list = new ArrayList<>();
//		while (null != head) {
//			list.add(head.val);
//			head = head.next;
//		}
//		int start = 0, end = list.size() - 1;
//		while (start < end) {
//			if (!Objects.equals(list.get(start), list.get(end))) {
//				return false;
//			}
//			start++;
//			end--;
//		}
//		return true;
//	}

	// 234 回文链表
	// 找到中点 翻转中点右边部分 再比较
	public boolean isPalindrome(ListNode head) {
		ListNode slow = head, fast = head;
		while (null != fast && null != fast.next) {
			slow = slow.next;
			fast = fast.next.next;
		}
		if (null != fast) {
			slow = slow.next;
		}
		ListNode left = head;
		ListNode right = pReverse(slow);
		while (null != right) {
			if (left.val != right.val) {
				return false;
			}
			left = left.next;
			right = right.next;
		}
		return true;
	}

	public ListNode pReverse(ListNode head) {
		ListNode pre = null, cur = head;
		while (null != cur) {
			ListNode tmp = cur.next;
			cur.next = pre;
			pre = cur;
			cur = tmp;
		}
		return pre;
	}

	// 2095
	public ListNode deleteMiddle(ListNode head) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		// 慢指针、快指针、前置指针
		ListNode slow = dummy;
		ListNode fast = dummy;
		ListNode pre = dummy;
		while (null != fast && null != fast.next) {
			if (null != fast.next.next) {
				pre = pre.next;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		if (null != fast) {
			pre = slow;
			slow = slow.next;
		}
		pre.next = slow.next;
		return dummy.next;
	}

}
