package com.william.schoolapp.ui.utils

import kotlin.random.Random

class SchoolImagesProvider {
    companion object {
        // Define a list of static URLs
        private val urls: MutableList<String> = mutableListOf(
            "https://takapuna.school.nz/images/1600x600/pages/Main_Building_with_students_cropped_for_website.jpg",
            "https://www.ags.school.nz/wp-content/uploads/2021/09/auckland-grammar-school-3527.jpg",
            "https://www.newstalkzb.co.nz/media/22450411/the-body-of-an-adult-male-was-discovered-at-westlake-boys-high-school-on-saturday-photo-google-maps.png?rmode=crop&rnd=132651509214570000&height=379&quality=95&scale=both",
            "https://upload.wikimedia.org/wikipedia/commons/4/45/The_Cathedral_Grammar_School%2C_Christchurch%2C_New_Zealand.jpg",
            "https://www.cbhs.school.nz/wp-content/uploads/2020/08/Original-Snow-Picture.jpg",
            "https://www.formnz.co.nz/images/il-957-3._RC_Maths_Block_smaller.jpg",
            "https://www.kings.school.nz/userfiles/image/centenial-building/the-centennial-building.jpg",
            "https://northcoteps.vic.edu.au/images/northcote-primary-20.jpg",
            "https://www.silverdaleprimary.school.nz/wp-content/uploads/sites/120/2019/11/SPORTS-HUB-e1575409114324.jpeg",
            "https://kingsway.school.nz/wp-content/uploads/2023/09/20210516_KWS-shoot8640-Cropped-for-Website-scaled.jpg",
            "https://study.nz/wp-content/uploads/2018/03/campus-feature-web-1024x552.jpg",
            "https://assets.ibaustralasia.org/schoolimages/47/massive_jpg_KristinSchool3.jpg",
            "https://www.poynter.co.nz/slir/c4.3/images/article_images/1691979653.jpg",
            "https://imagescdn.homes.com/i2/sElxMNy4Gg18ag8xbrL6kfvjC-SVEnLKLvWcdsLQRGk/117/mclean-high-school-mclean-va.jpg?p=1",
        )

        fun getUrl(): String {
            return urls[Random.nextInt(urls.size)]
        }
    }
}