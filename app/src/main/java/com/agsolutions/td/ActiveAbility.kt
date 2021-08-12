package com.agsolutions.td

class ActiveAbility (val id: Int, val name: String, val image: Int, var cd: Float, var cdRemain: String) {

    companion object {

        var aAid0 = ActiveAbility(7000, "Active Ability Moon Talent", R.drawable.moontransparent, 10800f, "0")
        var aAid1 = ActiveAbility(7001, "Bomb", R.drawable.bombgrey, 0f, "0")
        var aAid2 = ActiveAbility(7002, "Helping Hand", R.drawable.helpinghandred, 3600f, "0")
        var aAid3 = ActiveAbility(7003, "Helping Hand", R.drawable.helpinghandpurple, 3600f, "0")

    }
}