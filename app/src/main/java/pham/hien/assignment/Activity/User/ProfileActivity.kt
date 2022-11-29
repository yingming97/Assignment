package pham.hien.assignment.Activity.User

import pham.hien.assignment.Activity.Main.BaseActivity
import pham.hien.assignment.ViewModel.ProfileViewModel
import pham.hien.assignment.databinding.ActivityProfileBinding

class ProfileActivity :
    BaseActivity<ActivityProfileBinding, ProfileViewModel>(ActivityProfileBinding::inflate) {

    override fun initListener() {
    }

    override fun getViewModel(): Class<ProfileViewModel> {
        return ProfileViewModel::class.java
    }

    override fun initObserver() {
    }

    override fun initDataDefault() {
    }
}