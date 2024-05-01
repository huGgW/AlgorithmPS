class Solution 
{
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) 
    {
        vector<int> *result = new vector<int>();
        vector<int> *window = new vector<int>(nums.begin(), nums.begin() + k);
        sortVector(*window, 0, window->size()-1);
        
        for (int beg = 0; beg < nums.size() - k + 1; beg++)
        {
            if (beg > 0)
            {
                // update sliding window
                int removeIdx = binarySearch(*window, nums[beg-1], 0, k-1);
                window->erase(window->begin()+removeIdx);
                
                int insertIdx = binarySearch(*window, nums[beg+k-1], 0, k-2);
                window->insert(window->begin()+insertIdx, nums[beg+k-1]);
            }

            int windowMaxVal = (*window)[window->size()-1];
            result->push_back(windowMaxVal);
        }

        delete window;
        return *result;
    }

private:
    void sortVector(vector<int> &nums, int lo, int hi) 
    {
        if (lo > hi) 
        {
            return;
        }

        int mid = (lo + hi) / 2;
        swap(nums[mid], nums[hi]);

        int i = lo - 1;
        for (int j = lo; j < hi; j++) 
        {
            if (nums[j] < nums[hi]) 
            {
                swap(nums[++i], nums[j]);
            }
        }

        swap(nums[++i], nums[hi]);
        sortVector(nums, lo, i-1);
        sortVector(nums, i+1, hi);
    }

    int binarySearch(vector<int> &nums, int val, int lo, int hi) 
    {
        if (lo > hi) 
        {
            return lo;
        }

        int mid = (lo + hi) / 2;
        int midVal = nums[mid];

        if (val < midVal)
        {
            return binarySearch(nums, val, lo, mid-1);
        }
        else if (midVal < val)
        {
            return binarySearch(nums, val, mid+1, hi);
        }
        else
        {
            return mid;
        }
    }

    void swap(int &a, int &b) 
    {
        int tmp = b;
        b = a;
        a = tmp;
    }
};