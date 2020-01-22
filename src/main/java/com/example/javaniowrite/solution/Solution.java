package com.example.javaniowrite.solution;


import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhaoliancan
 * @version 1.0.0
 * @ClassName Solution.java
 * @Description TODO
 * @createTime 2019年08月23日 08:57:00
 */
public class Solution {


    private TreeNode root;

    private static CountDownLatch latch = new CountDownLatch(10);

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode() {
        }
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {

        return isMirror(root, root);

    }


    /**
     * 向二叉树插入数据
     *
     * @param value
     */
    public void insert(int value) {
        TreeNode newNode = new TreeNode();
        newNode.val = value;
        if (root == null) {
            root = newNode;
        } else {
            // 作为预指针,二叉树构造过程需要指针移动
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (value < current.val) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    // 插入右节点
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }

        }
    }

    /**
     * 把二叉搜索树转换成累加树
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            dfs(root, 0);
        }
        return root;
    }

    // 逆中序遍历节点计算累计值
    private int dfs(TreeNode node, int sum) {

        if (node == null) {
            return sum;
        }
        // 右子树累计值
        sum = dfs(node.right, sum);
        // 当前节点值+右子树累计值
        node.val = node.val + sum;
        sum = node.val;
        // 当前节点值+左子树累计值
        sum = dfs(node.left, sum);
        return sum;
    }

    /**
     * 如果同时满足下面的条件，两个树互为镜像：
     * <p>
     * 它们的两个根结点具有相同的值。
     * 每个树的右子树都与另一个树的左子树镜像对称。
     *
     * @param t1
     * @param t2
     * @return
     */
    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return t1.val == t2.val && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }


    /**
     * 反转单词顺序
     *
     * @param str ""hello world"
     * @return "world hello"
     */
    public static String reverseStr(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] strings = str.split(" ");
        for (int i = strings.length - 1; i >= 0; i--) {
            sb.append(strings[i]).append(" ");
        }
        return sb.toString();

    }

    /**
     * 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 设置预先指针，使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
        ListNode pre = new ListNode(0);
        // 变量追踪进位
        int carry = 0;
        ListNode curr = pre;
        while (l1 != null || l2 != null) {
            int x = (l1 == null) ? 0 : l1.val;
            int y = (l2 == null) ? 0 : l2.val;
            int sum = x + y + carry;
            // 更新进位值
            carry = sum / 10;
            // 创建一个数值为 (sum \bmod 10)(summod10) 的新结点
            curr.next = new ListNode(sum % 10);
            // 将当前结点前进到下一个结点。
            curr = curr.next;
            // p,q前进一个节点
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return pre.next;

    }


    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode curr = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + carry;
            // 更新进位值
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            curr.next = new ListNode(carry);
        }
        return pre.next;

    }

    private static List<String> output = new ArrayList<String>();

    /**
     * 电话号码的字母组合,使用回溯思想
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        if (digits.length() != 0) {
            backtrack("", digits);

        }
        return output;

    }


    public static void backtrack(String combination, String next_digits) {

        Map<String, String> dataMap = getMap();
        if (next_digits.length() == 0) {
            output.add(combination);
        } else {
            String digiti = next_digits.substring(0, 1);
            String letters = dataMap.get(digiti);
            for (int i = 0; i < letters.length(); i++) {
                String letter = dataMap.get(digiti).substring(i, i + 1);
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    private static Map<String, String> getMap() {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("2", "abc");
        dataMap.put("3", "def");
        dataMap.put("4", "ghi");
        dataMap.put("5", "jkl");
        dataMap.put("6", "mno");
        dataMap.put("7", "pqrs");
        dataMap.put("8", "tuv");
        dataMap.put("9", "wxyz");
        return dataMap;
    }

    /**
     * 反转二叉树,迭代
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode current = nodeQueue.poll();
            TreeNode tmp = current.left;
            current.left = current.right;
            current.right = tmp;
            if (current.left != null) {
                nodeQueue.add(current.left);
            }
            if (current.right != null) {
                nodeQueue.add(current.right);
            }
        }
        return root;

    }

    /**
     * 递归反转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        } else {

            TreeNode right = invertTree1(root.right);
            TreeNode left = invertTree1(root.left);
            root.left = right;
            root.right = left;
            return root;
        }

    }

    /**
     * 移动零,快慢指针
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int curr = 0;
        // 指针指向当前元素，如果新找到的元素不是 0，我们就在最后找到的非 0 元素之后记录它。
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[curr] = nums[i];
                curr++;
            }
        }
        //在 “cur” 索引到达数组的末尾之后，我们现在知道所有非 0 元素都已按原始顺序移动到数组的开头
        for (int i = curr; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    private static Date getYesterday() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        date = calendar.getTime();
        return date;
    }

    private Integer max = 0;

    private int depth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int letfDepth = depth1(root.left);
        int rightDepth = depth1(root.right);
        max = Math.max(letfDepth + rightDepth, max);
        return Math.max(letfDepth, rightDepth) + 1;
    }

    /**
     * 递归求二叉树的直径，一个节点的最大直径 = 它左树的高度 +  它右树的高度，二叉树直径=节点最大直径+根节点
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {

        depth(root);
        return max;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        max = Math.max(leftDepth + rightDepth, max);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 合并二叉树，使用递归
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode treeNode = new TreeNode(t1.val + t2.val);
        treeNode.left = mergeTrees(t1.left, t2.left);
        treeNode.right = mergeTrees(t1.right, t2.right);
        return treeNode;
    }

    public static Thread getThread() {
        return new Thread(() -> {
            try {
                System.out.println("线程开始等待");
                latch.await();
                System.out.println("线程等待结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 验证回文串
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            // 跳过非法字符
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;

            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;

    }

    public int lengthOfLastWord(String s) {
        if (StringUtils.isEmpty(s)) {
            return 0;
        }
        String[] strings = s.split(" ");
        if (strings.length == 0) {
            return 0;
        } else {
            String s1 = strings[strings.length - 1];
            return s1.length();
        }

    }

    public static int lengthOfLastWord1(String s) {
        String s1 = s.trim();
        int end = s1.length() - 1;
        if (end < 0) {
            return 0;
        }
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }

    /**
     * 二分法查找元素插入位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 杨辉三角
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> lists = new ArrayList<>();
        if (numRows == 0) {
            return lists;
        }
        lists.add(new ArrayList<>());
        lists.get(0).add(1);
        for (int numRow = 1; numRow < numRows; numRow++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            List<Integer> preRow = lists.get(numRow - 1);
            for (int j = 1; j < numRow; j++) {
                row.add(preRow.get(j - 1) + preRow.get(j));
            }
            row.add(1);
            lists.add(row);
        }
        return lists;

    }

    public static List<Integer> getRow(int rowIndex) {

        List<Integer> pre = new ArrayList<>();
        pre.add(1);
        for (int i = 0; i < rowIndex; i++) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(1);

            for (int j = 0; j < i; j++) {
                tmp.add(pre.get(j) + pre.get(j + 1));
            }
            tmp.add(1);
            pre = tmp;
        }
        return pre;

    }

    public void rotate1(int[] nums, int k) {
        int pre, tmp;
        for (int i = 0; i < k; i++) {
            pre = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                tmp = pre;
                pre = nums[j];
                nums[j] = tmp;
            }
        }
    }


    /**
     * 旋转数组,旋转k次，则k%n个元素被移到头部，剩余的往后移动
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {

        if (nums.length > 0) {
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }


    /**
     * 数组是有序数组，使用快慢指针寻找两数之和
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {

        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum < target) {
                low++;
            } else if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else {
                high--;
            }
        }
        throw new RuntimeException("error");
    }

    /**
     * 缺失的数字
     *
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {

        Arrays.sort(nums);
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                target++;
            } else {
                break;
            }
        }
        return target;
        /**方法二 高斯求和公式**/
//        int exceptSum = nums.length * (nums.length + 1) / 2;
//        int sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            sum += nums[i];
//        }
//        return exceptSum - sum;


    }

    public static int missingNumber1(int[] nums) {
        int exceptSum = nums.length * (nums.length + 1) / 2;
        int realSum = 0;
        for (int i = 0; i < nums.length; i++) {
            realSum += nums[i];
        }
        return exceptSum - realSum;
    }

    /**
     * 维护一个哈希表，里面始终最多包含 k 个元素，当出现重复值时则说明在 k 距离内存在重复元素
     * 每次遍历一个元素则将其加入哈希表中，如果哈希表的大小大于 k，则移除最前面的数字
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> integerSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (integerSet.contains(nums[i])) {
                return true;
            }
            integerSet.add(nums[i]);
            if (integerSet.size() > k) {
                integerSet.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 遍历
     * count 为当前元素峰值，maxCount为最大峰值
     * 初始化 count = 1
     * 从 0 位置开始遍历，遍历时根据前后元素状态判断是否递增，递增则 count++，递减则 count=1
     * 如果 count>maxCount，则更新 maxCount
     * 直到循环结束
     *
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {

        if (nums.length <= 1) {
            return nums.length;
        }
        int count = 1;
        int maxCount = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) {
                count++;
            } else {
                count = 1;
            }
            maxCount = count > maxCount ? count : maxCount;
        }
        return maxCount;

    }

    /**
     * 最大连续1的个数
     *
     * @param nums
     * @return
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
                maxCount = Math.max(count, maxCount);
            } else {
                count = 0;
            }
        }
        return maxCount;

    }

    /**
     * 按奇偶排序数组
     *
     * @param A
     * @return
     */
    public static int[] sortArrayByParity(int[] A) {

        int[] arrays = new int[A.length];
        int i = 0, k = A.length - 1;
        for (int j = 0; j < A.length; j++) {
            if (A[j] % 2 == 0) {
                arrays[i] = A[j];
                i++;
            } else {
                arrays[k] = A[j];
                k--;
            }
        }
        return arrays;
    }

    /**
     * 盛最多水的容器
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;

    }

    /**
     * 旋转图像
     * 先转置矩阵，然后翻转每一行
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {

        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }

    /**
     * 寻找中心索引
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {

        int totalSum = 0;
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (leftSum * 2 == totalSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    /**
     * 逐个枚举，空集的幂集只有空集，每增加一个元素，让之前幂集中的每个集合，追加这个元素，就是新增的子集。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<>(res.get(i));
                newSub.add(n);
                res.add(newSub);
            }
        }
        return res;

    }

    /**
     * 寻找多次出现的数字
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates(int[] nums) {
        // 方法一
//        Set<Integer> integerSet = new HashSet<>();
//        List<Integer> integerList = new LinkedList<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (integerSet.contains(nums[i])) {
//                integerList.add(nums[i]);
//            } else {
//                integerSet.add(nums[i]);
//            }
//        }
//        return integerList;
        List<Integer> arrayList = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] <= len && (nums[i] - 1) != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] - 1 != i) {
                arrayList.add(nums[i]);
            }
        }
        return arrayList;
    }

    private static void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }


    private int[] preorder;
    private int[] inorder;
    private int[] postorder;
    private int preIndex = 0;
    private int lastIndex = 0;
    private HashMap<Integer, Integer> inorderMap = new HashMap<>();
    private HashMap<Integer, Integer> postorderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder == null || inorder == null) {
            return null;
        }
        this.preorder = preorder;
        this.inorder = inorder;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(0, inorder.length - 1);

    }

    private TreeNode buildTree(int start, int end) {
        if (start > end) {
            return null;
        }
        int preVal = preorder[preIndex];
        TreeNode root = new TreeNode(preVal);
        int index = inorderMap.get(preVal);
        preIndex++;
        root.left = buildTree(start, index - 1);
        root.right = buildTree(index + 1, end);
        return root;
    }

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null) {
            return null;
        }
        this.inorder = inorder;
        this.postorder = postorder;
        lastIndex = postorder.length - 1;
        for (int i = 0; i < postorder.length; i++) {
            postorderMap.put(postorder[i], i);
        }
        return buildTree1(0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode buildTree1(int inLeft, int inRight, int postLeft, int postRight) {
        if (inLeft > inRight || postLeft > postRight) {
            return null;
        }
        int rootVal = postorder[lastIndex];
        TreeNode root = new TreeNode(rootVal);
        int index = postorderMap.get(rootVal);
        preIndex--;
        root.left = buildTree1(inLeft, index - 1, postLeft, postRight - inRight + index - 1);
        root.right = buildTree1(index + 1, inRight, postRight - inRight + index, postRight - 1);
        return root;
    }


    List<List<Integer>> res = new ArrayList<>();
    int[] candidates;
    int len;

    /**
     * 回溯+剪枝算法计算组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        if (candidates.length == 0) {
            return res;
        }
        this.candidates = candidates;
        this.len = candidates.length;
        Arrays.sort(candidates);
        findResult(target, 0, new Stack<>());
        return res;
    }

    public void findResult(int residue, int start, Stack<Integer> pre) {

        if (residue == 0) {
            // Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
            res.add(new ArrayList<>(pre));
            return;
        }
        // residue - candidates[i] 表示下一轮的剩余，如果下一轮的剩余都小于 0 ，就没有必要进行后面的循环了
        // 这一点基于原始数组是排序数组的前提，因为如果计算后面的剩余，只会越来越小
        for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
            pre.add(candidates[i]);
            // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
            findResult(residue - candidates[i], i, pre);
            pre.pop();
        }

    }

    /**
     * 动态规划计算组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Set<List<Integer>>> map = new HashMap<>();
        //对candidates数组进行排序
        Arrays.sort(candidates);
        int len = candidates.length;
        for (int i = 1; i <= target; i++) {
            //初始化map
            map.put(i, new HashSet<>());
            //对candidates数组进行循环
            for (int j = 0; j < len && candidates[j] <= target; j++) {
                if (i == candidates[j]) {
                    //相等即为相减为0的情况，直接加入set集合即可
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    map.get(i).add(temp);
                } else if (i > candidates[j]) {
                    //i-candidates[j]是map的key
                    int key = i - candidates[j];
                    //使用迭代器对对应key的set集合进行遍历
                    //如果candidates数组不包含这个key值，对应的set集合会为空，故这里不需要做单独判断
                    for (Iterator iterator = map.get(key).iterator(); iterator.hasNext(); ) {
                        List list = (List) iterator.next();
                        //set集合里面的每一个list都要加入candidates[j]，然后放入到以i为key的集合中
                        List tempList = new ArrayList<>();
                        tempList.addAll(list);
                        tempList.add(candidates[j]);
                        //排序是为了通过set集合去重
                        Collections.sort(tempList);
                        map.get(i).add(tempList);
                    }
                }
            }
        }
        result.addAll(map.get(target));
        return result;
    }

    /**
     * 动态规划计算不同路径
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        //法一
//
//        int[][] dp = new int[m][n];
//        for (int i = 0; i < m; i++) {
//            dp[i][0] = 1;
//        }
//        for (int i = 0; i < n; i++) {
//            dp[0][i] = 1;
//        }
//
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
//            }
//        }
//        return dp[m - 1][n - 1];
        // 法二
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];


    }

    /**
     * 动态规划计算最小路径和
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
        // 倒序
//        for (int i = m - 1; i >= 0; i--) {
//            for (int j = n - 1; j >= 0; j--) {
//                if (i == grid.length - 1 && j != grid[0].length - 1) {
//                    dp[i][j] = grid[i][j] + dp[i][j + 1];
//                } else if (i != grid.length - 1 && j == grid[0].length - 1) {
//                    dp[i][j] = grid[i][j] + dp[i + 1][j];
//                } else if (i != grid.length - 1 && j != grid[0].length - 1) {
//                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
//                } else {
//                    dp[i][j] = grid[i][j];
//                }
//            }
//        }
//        return dp[0][0];
    }

    /**
     * 除自身以外数组的乘积
     *
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {

        int p = 1, q = 1;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = p;
            p *= nums[i];
        }
        for (int i = nums.length - 1; i > 0; i--) {
            q *= nums[i];
            res[i - 1] *= q;
        }
        return res;
    }

    public int[] searchRange(int[] nums, int target) {

        int p = -1, q = -1;
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                p = i;
            }
        }
        for (int j = nums.length - 1; j >= 0; j--) {
            if (target == nums[j]) {
                q = j;
            }
        }
        return new int[]{q, p};

    }

    private static int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target || (left && nums[mid] == target)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * 二分法查找元素位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange1(int[] nums, int target) {
        int[] targetRange = {-1, -1};
        int leftIndex = extremeInsertionIndex(nums, target, true);
        if (leftIndex == nums.length || nums[leftIndex] != target) {
            return targetRange;
        }
        targetRange[0] = leftIndex;
        targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;
        return targetRange;

    }

    /**
     * 动态规划构造搜索树
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    /**
     * 颜色分类，荷兰国旗问题
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {

        int p0 = 0, p2 = nums.length - 1, curr = 0;
        int temp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                temp = nums[curr];
                nums[curr] = nums[p0];
                nums[p0] = temp;
                p0++;
                curr++;
            } else if (nums[curr] == 2) {
                temp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2] = temp;
                p2--;
            } else {
                curr++;
            }
        }

    }

    /**
     * 三角形最小路径和
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] minLen = new int[row + 1];
        for (int i = row - 1; i >= 0; i--) {
            // 第i行有i+1个数字
            for (int j = 0; j <= i; j++) {
                minLen[j] = Math.min(minLen[j], minLen[j + 1]) + triangle.get(i).get(j);
            }
        }
        return minLen[0];

    }

    /**
     * 字母异位词分组
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {

        if (strs.length == 0) {
            return new ArrayList<>();
        }
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String tmpStr = String.valueOf(chars);
            if (!hashMap.containsKey(tmpStr)) {
                hashMap.put(tmpStr, new ArrayList<>());
                hashMap.get(tmpStr).add(strs[i]);
            } else {
                hashMap.get(tmpStr).add(strs[i]);
            }
        }
        return new LinkedList<>(hashMap.values());
    }

    /**
     * 计数质数
     *
     * @param n
     * @return
     */
    public static int countPrimes(int n) {

        int count = 0;
        boolean[] signs = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!signs[i]) {
                count++;
                for (int j = i + i; j < n; j += i) {
                    //排除不是质数的数
                    signs[j] = true;
                }
            }
        }
        return count;

    }


    public static void main(String[] args) {
        //  String s = "a";
        String[] nums = {"eat", "tea", "tan", "ate", "nat", "bat"};
        //int result = searchInsert(nums, 2);
        //List<Integer> result = getRow(3);
        int count = 10;

        //int result = lengthOfLastWord1(s);
        List<List<String>> listList = groupAnagrams(nums);
        System.out.println(listList);
        long start = System.currentTimeMillis();
        int result = countPrimes(count);
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start));
        System.out.println(result);
//        String string = "23484";
//        int [] ints={0,4,5,7,0,8};
//        //List<String> str = letterCombinations(string);
//        //System.out.println("结果" + str);
//        moveZeroes(ints);
        // Date date=getYesterday();
//        String date = "20190802";
//        SimpleDateFormat sb = new SimpleDateFormat("yyyyMMdd");
//        try {
//            Date date1 = sb.parse(date);
//            System.out.println(date1);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println("启动计数线程");
//        System.out.println("计数值：" + latch.getCount());
//        getThread().start();
//        while (latch.getCount() != 0) {
//            latch.countDown();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("计数值：" + latch.getCount());
//        }
    }


}
