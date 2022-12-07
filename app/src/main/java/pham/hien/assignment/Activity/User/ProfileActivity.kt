package pham.hien.assignment.Activity.User

import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import pham.hien.assignment.Activity.Main.BaseActivity
import pham.hien.assignment.Model.User
import pham.hien.assignment.R
import pham.hien.assignment.Utils.SharedPrefUtils
import pham.hien.assignment.View.ViewLiked
import pham.hien.assignment.View.ViewPosted
import pham.hien.assignment.ViewModel.ProfileViewModel
import pham.hien.assignment.databinding.ActivityProfileBinding
import ppham.hien.assignment.Utils.gone
import ppham.hien.assignment.Utils.visible

class ProfileActivity :
    BaseActivity<ActivityProfileBinding, ProfileViewModel>(ActivityProfileBinding::inflate) {

    private val TAG = "YingMing"

    private lateinit var viewPosted: ViewPosted
    private lateinit var viewLiked: ViewLiked

    private lateinit var mUser: User
    private lateinit var viewPagerAdapter: ViewPageViewAdapter
    private var mListView = ArrayList<ViewGroup>()

    override fun initListener() {
        binding.imvBack.setOnClickListener(this)
        binding.btnPosted.setOnClickListener(this)
        binding.btnLiked.setOnClickListener(this)
    }

    override fun getViewModel(): Class<ProfileViewModel> {
        return ProfileViewModel::class.java
    }

    override fun initObserver() {
    }

    override fun initDataDefault() {
//        mUser = SharedPrefUtils.getLoginInformationUser(this)
        mUser = User("Phạm Văn Hiển",
            "kaiyuan997@gmail.com",
            "https://lh3.googleusercontent.com/a/ALm5wu0UHBfsZMhlnxHrWBNsj8PeNLPnBealqgmGmn37Fg=s96-c",
            "W0uptWWRzqXQUAwTQ94oIzldah22")
        binding.tvName.text = mUser.name
        Glide.with(this).load(mUser.avatar).placeholder(R.drawable.img_avatar_default)
            .into(binding.imvAvatar)
        initViewGroup()
    }

    override fun onClick(v: View) {
        when (v) {
            binding.imvBack -> {
                finish()
            }
            binding.btnPosted -> {
                setSelected(0)
            }
            binding.btnLiked -> {
                setSelected(1)
            }
        }
    }

    private fun setSelected(index: Int) {
        when (index) {
            0 -> {
                binding.btnPosted.setTextColor(Color.parseColor("#FFFFFF"))
                binding.btnPosted.setBackgroundResource(R.drawable.bg_selected)
                binding.btnLiked.setTextColor(Color.parseColor("#424242"))
                binding.btnLiked.setBackgroundResource(R.drawable.bg_unselected)
                binding.vpLayout.currentItem = 0
            }
            1 -> {
                binding.btnPosted.setTextColor(Color.parseColor("#424242"))
                binding.btnPosted.setBackgroundResource(R.drawable.bg_unselected)
                binding.btnLiked.setTextColor(Color.parseColor("#FFFFFF"))
                binding.btnLiked.setBackgroundResource(R.drawable.bg_selected)
                binding.vpLayout.currentItem = 1
            }
        }
    }

    private fun initViewGroup() {
        viewPosted = ViewPosted(this)
        viewLiked = ViewLiked(this)
        viewPosted.openForTheFirstTime(this)
        viewLiked.openForTheFirstTime(this)
        mListView.add(viewPosted)
        mListView.add(viewLiked)
        viewPagerAdapter = ViewPageViewAdapter(mListView)
        binding.vpLayout.adapter = viewPagerAdapter
        binding.ivPostedLiked.setupWithViewPager(binding.vpLayout)
        binding.vpLayout.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                setSelected(binding.vpLayout.currentItem)
            }
        })
    }
}