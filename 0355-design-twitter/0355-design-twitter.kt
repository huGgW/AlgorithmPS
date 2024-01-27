class Twitter() {
    val followings = mutableMapOf<Int, MutableSet<Int>>()
    val tweets = mutableListOf<Pair<Int, Int>>()

    fun postTweet(userId: Int, tweetId: Int) {
        tweets.add(userId to tweetId)
    }

    fun getNewsFeed(userId: Int): List<Int> {
        val followersFeed =  tweets.filter { 
           followings[userId]?.contains(it.first) ?: false
            || userId == it.first
        }.reversed()
        .map { it.second }
        
        return if (followersFeed.size >= 10) {
            followersFeed.subList(0, 10)
        } else {
            followersFeed
        }
    }

    fun follow(followerId: Int, followeeId: Int) {
        followings[followerId]?.add(followeeId)
            ?: let {
                followings[followerId] = mutableSetOf(followeeId)
            }
    }

    fun unfollow(followerId: Int, followeeId: Int) {
        followings[followerId]?.remove(followeeId)
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * var obj = Twitter()
 * obj.postTweet(userId,tweetId)
 * var param_2 = obj.getNewsFeed(userId)
 * obj.follow(followerId,followeeId)
 * obj.unfollow(followerId,followeeId)
 */
