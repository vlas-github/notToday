(function () {
    'use strict';

    var localizedTextsEn = function (localizedTexts) {
        localizedTexts['en'] = {
            'close': 'Close',
            'login': 'Login',
            'login.form.registration': 'registration',
            'login.message.error': 'Not valid email or password. Check that you have entered the data correctly, or contact your administrator',
            'login.message.info': 'Enter your email and password that you used to login or go to the ',
            'login.user.name':'User Name:',
            'login.user.password': 'Password:',
            'login.user.remember': 'Remember me',
            'logout': 'Logout',
            'logout.message': 'You are out of the system, which would come back, go to ',
            'logout.message.login': 'login',
            'page.about': 'About',
            'page.home': 'Home',
            'page.main.message': 'Welcome to the "Not Today" - a web service allows you to manage a list of tasks',
            'page.main.message.2': 'On this page will appear project news, new features and important changes',
            'page.name.message.news': 'Project news:',
            'page.tasks': 'Tasks',
            'profile': 'Profile',
            'profile.edit': 'Edit profile',
            'profile.edit.button': 'Edit profile',
            'profile.email': 'Email',
            'profile.locality': 'Locality',
            'profile.name': 'Name',
            'profile.save': 'Save profile',
            'profile.subscribe': 'Subscribe to email newsletter',
            'profile.your': 'Your profile',
            'repeat.catalog.each.day': 'Each day',
            'repeat.catalog.each.month': 'Each month',
            'repeat.catalog.each.three.days': 'Each three days',
            'repeat.catalog.each.two.days': 'Each two days',
            'repeat.catalog.each.weekday': 'Each weekday',
            'repeat.catalog.each.week': 'Each week',
            'repeat.catalog.each.year': 'Each year',
            'save': 'Save',
            'save.and.continue': 'Save and continue',
            'task.add': 'Add new task',
            'task.add.fast': 'Fast task:',
            'task.add.fast.button.text': 'add',
            'task.add.fast.text': 'Input todo title',
            'task.complete': 'Complete',
            'task.completed': 'Completed',
            'task.date': 'Date',
            'task.delete': 'Delete',
            'task.deleted': 'Deleted',
            'task.description': 'Description',
            'task.edit': 'Edit task',
            'task.placeholder.date': 'Input date',
            'task.placeholder.description': 'Input description',
            'task.placeholder.title': 'Input task',
            'task.repeat': 'Repeat',
            'task.show': 'Show task',
            'task.time': 'Time',
            'task.title': 'Title',
            'tasks.all': 'All tasks',
            'tasks.new': 'New tasks',
            'tasks.completed': 'Completed tasks',
            'tasks.deleted': 'Deleted tasks'
        }
    }

    angular.module('todolistApp').
        config(['localizedTexts', localizedTextsEn]);

})();