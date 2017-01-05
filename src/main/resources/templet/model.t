${copyRight}
package ${pgName}.model;

import java.io.Serializable;
import com.google.common.base.Objects;

${classNote}
public class ${className} implements Serializable {

    private static final long serialVersionUID = -1L;

${classColumns}
${classConstructor}
${classGetterAndSetter}
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ${className} that = (${className}) o;
        return  ${equalsDetails};
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(${hashCode});
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
${toString}
                .toString();
    }
    
}
