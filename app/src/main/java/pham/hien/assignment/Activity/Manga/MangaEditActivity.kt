package pham.hien.assignment.Activity.Manga

import pham.hien.assignment.Activity.Main.BaseActivity
import pham.hien.assignment.ViewModel.MangaEditViewModel
import pham.hien.assignment.databinding.ActivityMangaEditBinding

class MangaEditActivity :
    BaseActivity<ActivityMangaEditBinding, MangaEditViewModel>(ActivityMangaEditBinding::inflate) {
    override fun initListener() {

    }

    override fun getViewModel(): Class<MangaEditViewModel> {
        return  MangaEditViewModel::class.java
    }

    override fun initObserver() {

    }

    override fun initDataDefault() {

    }
}