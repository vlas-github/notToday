(function () {
    'use strict';

    var localizedTextsRu = function (localizedTexts) {
        localizedTexts['ru'] = {
            'admin': 'Админка',
            'close': 'Отмена',
            'field.is.required': 'Это поле обязательно для заполнения',
            'field.is.not.valid': 'Это поле заполнено не правильно',
            'login': 'Вход',
            'login.form.registration': 'регистрации',
            'login.message.error': 'Имя или пароль введены не верно. Проверьте правильность введенных данных или обратитесь к администратору',
            'login.message.info': 'Введите имя (почту) и пароль которые вы используете для входа или перейдите на форму ',
            'login.user.name':'Имя',
            'login.user.email':'Почта',
            'login.user.login':'Логин',
            'login.user.password': 'Пароль',
            'login.user.remember': 'Не выходить из системы',
            'logout': 'Выйти',
            'logout.title': 'Выход',
            'logout.message': 'Вы вышли из системы, что бы вернуться, перейдите по ссылке ',
            'logout.message.login': 'войти',
            'news': 'Новости',
            'news.author': 'Автор',
            'news.englishText': 'Английский текст',
            'news.russianText': 'Русский текст',
            'news.type': 'Тип',
            'page.about': 'О проекте',
            'page.home': 'Главная',
            'page.main.message': 'Добро пожаловать на "Not Today" - веб-сервис позволяющий управлять вашими списками дел',
            'page.main.message.2': 'На этой странице будут появляться новости проекта, информация о новых фичах и важных изменениях',
            'page.name.message.news': 'Новости проекта:',
            'page.tasks': 'Задачи',
            'profile': 'Профиль',
            'profile.edit': 'Редактирование профиля',
            'profile.edit.button': 'Изменить',
            'profile.email': 'Электронная почта',
            'profile.locality': 'Язык',
            'profile.login': 'Логин',
            'profile.name': 'Имя',
            'profile.save': 'Сохранить',
            'profile.subscribe': 'Присылать уведомления на почту',
            'profile.your': 'Ваш профиль',
            'registration': 'Регистрация',
            'repeat.catalog.each.day': 'Каждый день',
            'repeat.catalog.each.month': 'Каждый месяц',
            'repeat.catalog.each.three.days': 'Каждые три дня',
            'repeat.catalog.each.two.days': 'Каждые два дня',
            'repeat.catalog.each.weekday': 'По будням',
            'repeat.catalog.each.week': 'Раз в неделю',
            'repeat.catalog.each.year': 'Раз в год',
            'save': 'Сохранить',
            'save.and.continue': 'Сохранить и продолжить',
            'settings': 'Настройки системы',
            'task.add': 'Добавить задачу',
            'task.add.fast': 'Новыя задача:',
            'task.add.fast.button.text': 'добавить',
            'task.add.fast.text': 'Название задачи',
            'task.complete': 'Выполнить',
            'task.completed': 'Выполнена',
            'task.date': 'Дата',
            'task.delete': 'Удалить',
            'task.deleted': 'Удаленна',
            'task.description': 'Описание',
            'task.edit': 'Редактирование задачи',
            'task.placeholder.date': 'Выберите дату',
            'task.placeholder.description': 'Введите описание',
            'task.placeholder.title': 'Введите задачу',
            'task.repeat': 'Повтор',
            'task.set.time': 'Установить время',
            'task.show': 'Просмотр задачи',
            'task.time': 'Время',
            'task.title': 'Название',
            'tasks.all': 'Всего задач',
            'tasks.new': 'Новые задачи',
            'tasks.completed': 'Завершенные задачи',
            'tasks.deleted': 'Удаленные задачи',
            'users': 'Пользователи'
        }
    }

    angular.module('todolistApp').
        config(['localizedTexts', localizedTextsRu]);

})();