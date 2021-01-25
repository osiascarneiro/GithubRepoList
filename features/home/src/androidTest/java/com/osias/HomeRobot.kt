package com.osias

import com.osias.githubrepos.testCore.robot.BaseRobot
import com.osias.home.R


fun home(func: HomeRobot.() -> Unit) = HomeRobot().apply { func() }

class HomeRobot: BaseRobot() {

    fun checkNineItemsInList() = checkNumberOfItensInList(R.id.repositoryList, 9)
    fun checkNoItemsInList() = checkNumberOfItensInList(R.id.repositoryList, 0)
    fun checkMockTitleInThirdPosition() = checkTextInPosition(R.id.repositoryList, 2, "2, 2")
    fun checkLoginInSeconPosition() = checkTextInPosition(R.id.repositoryList, 2, "owner_login")
    fun checkStarCountInFourthPosition() = checkTextInPosition(R.id.repositoryList, 3, "3")
    fun checkForkCountInFifthPosition() = checkTextInPosition(R.id.repositoryList, 4, "Forks: 4")

}