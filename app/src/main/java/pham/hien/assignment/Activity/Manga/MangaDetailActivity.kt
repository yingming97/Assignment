package pham.hien.assignment.Activity.Manga

import pham.hien.assignment.Activity.Main.BaseActivity
import pham.hien.assignment.ViewModel.MangaDetailViewModel
import pham.hien.assignment.databinding.ActivityMangaDetailBinding

class MangaDetailActivity :
    BaseActivity<ActivityMangaDetailBinding, MangaDetailViewModel>(ActivityMangaDetailBinding::inflate) {


    override fun initListener() {

    }

    override fun getViewModel(): Class<MangaDetailViewModel> {
        return MangaDetailViewModel::class.java
    }

    override fun initObserver() {
    }

    override fun initDataDefault() {
    }
}