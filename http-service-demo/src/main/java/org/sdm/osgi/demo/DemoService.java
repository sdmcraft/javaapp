package org.sdm.osgi.demo;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;


@Component(metatype = true, immediate = true, label = "Demo Service Component", description = "A demo service component for learning")
@Service(value = DemoService.class)
@Properties({@Property(name = "prop1", value = "value1")
    , @Property(name = "prop2", value = "value2")
})
public class DemoService
{
    @Activate
    protected void activator(ComponentContext componentContext)
    {
        System.out.println("DemoService activated!!");
    }

    @Deactivate
    protected void deactivator(ComponentContext componentContext)
    {
        System.out.println("DemoService deactivated!!");
    }

    @Modified
    protected void modified(ComponentContext componentContext)
    {
        System.out.println("DemoService modified!!");
    }
}
