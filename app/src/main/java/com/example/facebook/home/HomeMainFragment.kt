package com.example.facebook.home

//class HomeMainFragment : Fragment() {
//    lateinit var binding: FragmentHomeMainBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_main, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val friendsListAdapter = friendsListAdapter(::onaddClicked, ::onRemoveClicked)
//        val postAdapter = PostAdapter()
//        val data = listOf(
//            FriendsListResponse("tarun1", "lanka", ""),
//            FriendsListResponse("tarun2", "lanka", ""),
//            FriendsListResponse("tarun3", "lanka", "")
//        )
//        val postData = listOf(
//            PostsResponsesItem("ram", "1", "austrlia"),
//            PostsResponsesItem("athira", "2", "kerala"),
//            PostsResponsesItem("lovely", "3", "chennai"),
//        )
//        binding.recyclerViewFriends.adapter = friendsListAdapter
//        binding.recyclerViewPosts.adapter = postAdapter
//        friendsListAdapter.submitList(data)
//        postAdapter.submitList(postData)
//        binding.layoutCreatePost.searchView.setOnClickListener {
//            startActivity(Intent(requireContext(), CreatePostActivity::class.java))
//
//        }
//    }
//
//    private fun onRemoveClicked(item: FriendsListResponse) {
//        Toast.makeText(requireContext(), "clicked on remove button", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun onaddClicked(item: FriendsListResponse) {
//        Toast.makeText(requireContext(), "clicked on add button", Toast.LENGTH_SHORT).show()
//
//    }
//
//}