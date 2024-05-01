class Solution 
{
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) 
    {
        vector<int> *result = new vector<int>();
        priority_queue<pair<int, int>> *pq = new priority_queue<pair<int, int>>();
        
        for (int beg = 0; beg < nums.size() - k + 1; beg++)
        {
            if (beg > 0)
            {
                pq->push(make_pair(nums[beg+k-1], beg+k-1));
            }
            else
            {
                for (int i = beg; i < beg + k; i++)
                {
                    pq->push(make_pair(nums[i], i));
                }
            }

            while (true)
            {
                pair<int, int> pp = pq->top();

                if (beg <= pp.second && pp.second < beg+k)
                {
                    result->push_back(pp.first);
                    break;
                }
                else
                {
                    pq->pop();
                }
            }
        }

        return *result;
    }

};