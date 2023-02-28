package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.ArrayList;                                   //112-150
import java.util.Arrays;
import java.util.List;

public class ControllerImpl implements Controller {
    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploredCount = 0;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        if (type.equals("AnimalExplorer")){
            explorer = new AnimalExplorer(explorerName);
        }
        else if (type.equals("GlacierExplorer")){
            explorer = new AnimalExplorer(explorerName);
        }
        else if (type.equals("NaturalExplorer")){
            explorer = new AnimalExplorer(explorerName);
        }
        else {
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
        explorerRepository.add(explorer);
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
//        List<String>exList = Arrays.asList(exhibits.clone());
        for (String exhibit : exhibits){
            state.getExhibits().add(exhibit);
        }
        this.stateRepository.add(state);
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = this.explorerRepository.byName(explorerName);
        for (Explorer explorer1 : this.explorerRepository.getCollection()){
            if (explorer1.getName().equals(explorerName)){
                this.explorerRepository.remove(explorer);
                return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
            }
        }
            throw new IllegalArgumentException(String.format("Explorer %s doesn't exists.",explorerName));
        }

    @Override
    public String exploreState(String stateName) {
        State state = null;
        this.exploredCount++;
        for (State state1 : this.stateRepository.getCollection()){
            if (state1.getName().equals(stateName)){
                state = state1;
            }
        }
        List<Explorer>goodOnesList = new ArrayList<>();
        int retired = 0;
        for (Explorer explorer : this.explorerRepository.getCollection()){
            if (explorer.getEnergy() > 50){
                goodOnesList.add(explorer);
            }
        }
        if (goodOnesList.size() < 1){
            throw new IllegalArgumentException("You must have at least one explorer to explore the state.");
        }
        List<String>removedEx = new ArrayList<>();

        for (Explorer explorer : this.explorerRepository.getCollection()){
            while (explorer.canSearch()){
                assert state != null;
                for (String exhibit : state.getExhibits()){
                    explorer.search();
                    explorer.getSuitcase().getExhibits().add(exhibit);
                    removedEx.add(exhibit);
                    if (!explorer.canSearch()){
                        retired++;
                        break;
                    }
                }
            }
            for (String ex : removedEx){
                this.explorerRepository.remove(ex);
            }
        }
            return String.format("The state %s was explored. %d researchers have retired on this mission.",stateName, retired);
        }


    @Override
    public String finalResult() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%d states were explored.%n",this.exploredCount));
        builder.append("Information for the explorers:").append(System.lineSeparator());
        for (Explorer explorer : this.explorerRepository.getCollection()){
            builder.append(String.format("Name: %s%n", explorer.getName()));
            builder.append(String.format("Energy: %s%n", explorer.getEnergy()));
            builder.append("Suitcase exhibits: ");
            if (explorer.getSuitcase().getExhibits().size() < 1){
                builder.append("None");
            }
            else {
               builder.append(String.join(", ", explorer.getSuitcase().getExhibits()));
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
