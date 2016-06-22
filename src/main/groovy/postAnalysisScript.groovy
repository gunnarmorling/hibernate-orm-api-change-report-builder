def it = jApiClasses.iterator()
while (it.hasNext()) {
	def jApiClass = it.next()
	def fqn = jApiClass.getFullyQualifiedName()

	def methodIt = jApiClass.getMethods().iterator()
	while (methodIt.hasNext()) {
		def method = methodIt.next()

        if ( method.getOldMethod().isPresent() ) {
            for( p in method.getOldMethod().get().getParameterTypes() ) {
                if ( "org.hibernate.engine.spi.SessionImplementor".equals( p.getName() ) ) {
                    methodIt.remove()
                }
            }

            if ( "org.hibernate.engine.spi.SessionImplementor".equals( method.getOldMethod().get().getReturnType().getName() ) ) {
                methodIt.remove()
            }
        }
	}
}
return jApiClasses
