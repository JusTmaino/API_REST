package api_rest

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        "/api/books"(controller: 'api', action: 'book')
        "/api/books/$id"(controller: 'api', action: 'book')
        "/api/book/$id"(controller: 'api', action: 'book')

        "/api/libraries"(controller: 'api', action: 'library')
        group "/api/libraries",
                {
                    "/$idLibrary"(controller: 'api', action: 'library')
                    "/$idLibrary/books"(controller: 'api', action: 'book' , idLib :$idLibrary)
                    "/$idLibrary/book/$idBook"(controller: 'api', action: 'book' , idLib :$idLibrary)
                }
        group "/api/library",
                {
                    "/$idLibrary"(controller: 'api', action: 'library')
                    "/$idLibrary/books"(controller: 'api', action: 'book' , idLib :$idLibrary)
                    "/$idLibrary/book/$idBook"(controller: 'api', action: 'book' , idLib :$idLibrary)
                }

        "/api/doc"(controller: 'api', action: 'doc')
    }
}
